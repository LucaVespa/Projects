//
//  MatchMakerView.swift
//  ProximityMatchMaker
//
//  Created by Luca on 8/3/22.
//

import SwiftUI

struct MatchMakerView: View {
    
    private let defaults = UserDefaults.standard
    @State var name = ""
    
    var body: some View {
        Text(name).onAppear{
            loadName()}
    }
    
    func loadName(){
        let savedName = defaults.string(forKey: "name")
        name = savedName ?? ""
    }
    
}

struct MatchMakerView_Previews: PreviewProvider {
    static var previews: some View {
        MatchMakerView()
    }
}
