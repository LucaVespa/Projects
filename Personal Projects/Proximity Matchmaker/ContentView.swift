//
//  ContentView.swift
//  ProximityMatchMaker
//
//  Created by Luca on 7/21/22.
//
import Combine
import SwiftUI

struct ContentView: View {
    
    //For getting and updating location
    @StateObject var deviceLocationService = DeviceLocationService.shared
    @State var tokens: Set<AnyCancellable> = []
    @State var coordinates: (lat: Double, lon: Double) = (0, 0)
    let timer = Timer.publish(every: 60, on: .main, in: .common).autoconnect()
    
    //For Writing Data
    @State var content: String = ""
    @StateObject var writeData = WriteUserLocationData()
    @State var safetyDupePrevention = true //attempt at preventing two coordinates from being input by a user at one timeframe
    
    //For Reading and Storing Data
    private let defaults = UserDefaults.standard
    @StateObject var readUserData = ReadUserLocationData()
    @State var userList = [UserLocationData]()
    @State var tempTime = ""
    @State var tempCoords = ""
    @State var tempLat = 0.0
    @State var tempLong = 0.0
    @State var tempLatForSearch = 0
    @State var tempLongForSearch = 0
    @State var timeKeyValue = 62
    @State var tempTimeKeyValue = 62
    @State var idleCheck1 = ""
    @State var idleCheck2 = ""
    @State var idle = false
    
    //For Testing
    @State var testName = ""
    @State var testTime = ""
    @State var testKey = ""
    
    //For Deleting
    @StateObject var deleter = DeleteFirebaseItems()
    
    
    var body: some View {
        NavigationView{
            VStack{
                Spacer()
                Text("Lat: \(coordinates.lat)")
                
                Text("Long: \(coordinates.lon)")
                   
                //Get time and send to firebase every 3 minutes
                Text("Time: \(getTime())").onReceive(timer){ time in
                    if getTime()[1] == "M" {
                        timeKeyValue = (Int(getTime()[5] + getTime()[4])) ?? 2
                        }else{
                            timeKeyValue = (Int(getTime()[2] + getTime()[1])) ?? 2
                        }
                    if (timeKeyValue % 3 == 0) && (safetyDupePrevention == true) {
                        idleCheck2 = idleCheck1
                        idleCheck1 = tempCoords
                        tempTimeKeyValue = timeKeyValue
                        tempLat = coordinates.lat
                        tempLong = coordinates.lon
                        tempLatForSearch = Int(round(tempLat * 1000))
                        tempLongForSearch = Int(round(tempLong * 1000))
                        tempTime = getTime()
                        tempCoords = String(Int(round(tempLat * 1000)))+String(Int(round(tempLong * 1000)))
                        if (tempCoords == idleCheck1) && (idleCheck1 == idleCheck2){
                            idle = true
                        }else{idle = false}
                        writeData.pushCoord(lat: tempLat, long: tempLong, user: content, tim: tempTime, coords: tempCoords, timeKey: tempTimeKeyValue, idleness: idle)
                        safetyDupePrevention = false
                        
                        if timeKeyValue % 15 == 0{
                            readFromIdle(myName: content)
                            //DELETION IS CURRENTLY DONE CLIENT SIDE AND MUST BE CHANGED TO BE SERVER SIDE
                            deleter.scheduledDeletes(timeKey: timeKeyValue)
                        }
                        
                        
                    }
                    //Start reading 1 minute after each write
                    if (timeKeyValue % 3 == 1)  && (tempCoords != ""){
                        safetyDupePrevention = true
                        if !idle{
                            readAllNineBoxes(0, 0)
                            //DispatchQueue is weird so I did copy+paste instead of throwing it in a function
                            DispatchQueue.main.asyncAfter(deadline: .now() + 10){
                                readAllNineBoxes(1, 0)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 20){
                                readAllNineBoxes(-1, 0)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 30){
                                readAllNineBoxes(0, 1)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 40){
                                readAllNineBoxes(0, -1)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 50){
                                readAllNineBoxes(1, 1)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 60){
                                readAllNineBoxes(1, -1)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 70){
                                readAllNineBoxes(-1, 1)
                            }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 80){
                                readAllNineBoxes(-1, -1)
                            }
                            saveUserList()
                        }
                }
                }
                
                TextEditor(text: $content).frame(width: 400.0, height: 30.0).colorInvert() .multilineTextAlignment(.center)
            
                Button{saveName()} label:{ Text("Save Username")}
                    .padding(.top)
            
                Button{
                    if content != "" {writeData.pushCoordManual(lat: coordinates.lat, long: coordinates.lon, user: content, tim: getTime(), timeKey: timeKeyValue)}
                }label: {Text("Manual Send")}
                    .padding(.vertical)
                
                Button{
                    if !userList.isEmpty {
                        testName = userList[0].name
                        testTime = userList[0].time
                        testKey = userList[0].key
                        userList.removeFirst()
                        saveUserList()
                    }
                }label: {Text("Display Name: " + testName + " " + testTime + " " + testKey)}
                    .padding(.bottom)
                
                VStack{
                    NavigationLink{MatchMakerView()} label: {Text("Match Maker")}
                    Text(String(tempLatForSearch) + String(tempLongForSearch) + " " + String(tempLongForSearch + 1))
                    Button{readFromIdle(myName: content)} label:{ Text("Download from Idle")}
                        .padding(.top)
                    Spacer()
                    Spacer()
                }
                
            }
            
            }
        .onAppear {
            observeCoordinateUpdates()
            observeLocationAccessDenied()
            deviceLocationService.requestLocationUpdates()
            loadName()
            loadUserList()
        }
        
    }
    func observeCoordinateUpdates(){
        deviceLocationService.coordinatesPublisher.receive(on: DispatchQueue.main)
            .sink { completion in
            if case .failure(let error) = completion{
                print(error)
            }
            } receiveValue: { coordinates in
                self.coordinates = (coordinates.latitude, coordinates.longitude)
                
    }
        .store(in: &tokens)
    }
    
    func observeLocationAccessDenied(){
        deviceLocationService.deniedLocationAccessPublisher
            .receive(on: DispatchQueue.main)
            .sink {
                print("Show some kind of alert to the user")
                
            }
            .store(in: &tokens)
    }
    
    func getTime() -> String{
        let formatter = DateFormatter()
        formatter.timeStyle = .short
        let dateString = formatter.string(from: Date())
        return dateString
    }
    
    func saveName(){
        defaults.set(content, forKey: "name")
    }
    func loadName(){
        let savedName = defaults.string(forKey: "name")
        content = savedName ?? ""
    }
    
    func saveUserList(){
        do{
            let encoder = JSONEncoder()
            let data = try encoder.encode(userList)
            UserDefaults.standard.set(data, forKey: "SavedMatchData")
        } catch{
            print("Unable to Encode Array")
        }

    }
    
    func loadUserList(){
        if let data = UserDefaults.standard.data(forKey: "SavedMatchData"){
            do{
                let decoder = JSONDecoder()
                userList = try decoder.decode([UserLocationData].self, from: data)
        }catch{
            print("Could not Decode Array")
        }
    }
    }
    
    func readAllNineBoxes(_ latAdd: Int, _ longAdd: Int){
        readUserData.readListObject((String(tempLatForSearch + latAdd) + String(tempLongForSearch + longAdd)), tempTimeKeyValue)
        
        for object in readUserData.dataList{
            if object.isIdle{
                writeData.pushIdle(idleUsername: object.name, lat: tempLat, long: tempLong, user: content, tim: tempTime, coords: tempCoords, timeKey: tempTimeKeyValue, idleness: idle)
            }
            userList.append(object)
        }

        readUserData.dataList.removeAll()
    }
    
    func readFromIdle(myName: String){
        readUserData.readListObjectIdle(myName)
        for object in readUserData.dataListIdle{
            userList.append(object)
        }
        readUserData.dataListIdle.removeAll()
        deleter.deleteIdle(name: myName)
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

//Extension allows strings to be indexed from back to front (used to verify times)
extension String{
    subscript(i: Int) -> String {
        return String(self[index(endIndex, offsetBy: -i)])
    }
}
