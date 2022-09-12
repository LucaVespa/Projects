package transit;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	private TNode trainZero; // a reference to the zero node in the train layer

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */ 
	public Transit() { trainZero = null; }

	/* 
	 * Default constructor used by the driver and Autolab. 
	 * DO NOT use in your code.
	 * DO NOT remove from this file
	 */
	public Transit(TNode tz) { trainZero = tz; }
	
	/*
	 * Getter method for trainZero
	 *
	 * DO NOT remove from this file.
	 */
	public TNode getTrainZero () {
		return trainZero;
	}

	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0. Store the zero node in the train layer in
	 * the instance variable trainZero.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 */
	public void makeList(int[] trainStations, int[] busStops, int[] locations) {

	    // UPDATE THIS METHOD
		
		TNode trainFront = new TNode(0);
		TNode busFront = new TNode(0);
		TNode walkFront = new TNode(0);
		trainZero = trainFront;
		TNode trainZeroPTR = new TNode(0);
		trainZeroPTR = trainFront;
		TNode busZero = new TNode();
		busZero = busFront;
		TNode walkZero = new TNode();
		walkZero = walkFront;
		trainFront.setDown(busFront);
		busFront.setDown(walkFront);
		 

		for (int i = 0; i < trainStations.length; i++){
			
			TNode n = new TNode (trainStations[i]);
			trainFront.setNext(n);
			trainFront = n;
			
			
		}
		for (int i = 0; i < busStops.length; i++){
			
			TNode n = new TNode (busStops[i]);
			busFront.setNext(n);
			busFront = n;
			if (busFront.getLocation() == trainZeroPTR.getNext().getLocation()){
				trainZeroPTR.getNext().setDown(busFront);
				if(trainZeroPTR.getNext().getNext() != null){
				trainZeroPTR = trainZeroPTR.getNext();
				}
			}
			
			
		}
		for (int i = 0; i < locations.length; i++){
			TNode n = new TNode (locations[i]);
			walkFront.setNext(n);
			walkFront = n;

			if (walkFront.getLocation() == busZero.getNext().getLocation()){
				busZero.getNext().setDown(walkFront);
				if(busZero.getNext().getNext() != null){
				busZero = busZero.getNext();
				}
			}
		}

		
	}
	
	/**
	 * Modifies the layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param station The location of the train station to remove
	 */
	public void removeTrainStation(int station) {
	    TNode ptrFast = new TNode();
		ptrFast = trainZero.getNext();
		TNode ptrSlow = new TNode();
		ptrSlow = trainZero;

		do{
			if (ptrFast.getLocation() == station){
				
			 ptrSlow.setNext(ptrFast.getNext());
			 return;
			}
			ptrFast = ptrFast.getNext();
			ptrSlow = ptrSlow.getNext();

		}while (ptrFast != null);

		
	}

	/**
	 * Modifies the layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param busStop The location of the bus stop to add
	 */
	public void addBusStop(int busStop) {
	    // UPDATE THIS METHOD
		TNode newStop = new TNode(busStop);
		TNode busZero = new TNode();
		busZero = trainZero.getDown();
		TNode ptrFast = new TNode();
		ptrFast = busZero.getNext();
		TNode ptrSlow = new TNode();
		ptrSlow = busZero;

		TNode walkZero = new TNode();
		walkZero = busZero.getDown();

		do{
			if(ptrFast.getLocation() == busStop){
				return;
			}
			if (ptrFast.getLocation() > busStop){
			newStop.setNext(ptrFast);
			ptrSlow.setNext(newStop);
			ptrFast = null;
			}
			if (ptrFast != null){
			ptrFast = ptrFast.getNext();
			ptrSlow = ptrSlow.getNext();
			}
		}while (ptrFast != null);

		if(ptrSlow.getLocation() < busStop){
			ptrSlow.setNext(newStop);
		}

		do{
			if(walkZero.getLocation() == busStop){
				newStop.setDown(walkZero);
			}
			walkZero = walkZero.getNext();
		}while (walkZero != null);

	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param destination An int representing the destination
	 * @return
	 */
	public ArrayList<TNode> bestPath(int destination) {

	    // UPDATE THIS METHOD
		ArrayList<TNode> path = new ArrayList<TNode>();
		TNode ptr = new TNode();
		ptr = this.trainZero;

		while (ptr != null){
			path.add(ptr);
			if (ptr.getNext() == null){
				ptr = ptr.getDown();
			}
			else if (ptr.getNext().getLocation() > destination){
				ptr = ptr.getDown();
			}
			else{
				ptr = ptr.getNext();
			}
		}
		
	    return path;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @return A reference to the train zero node of a deep copy
	 */
	public TNode duplicate() {

	    // UPDATE THIS METHOD

		TNode newTrainZero = new TNode();
		ArrayList<Integer> trainArray = new ArrayList<Integer>();
		ArrayList<Integer> busArray = new ArrayList<Integer>();
		ArrayList<Integer> walkArray = new ArrayList<Integer>();

		TNode trainCopy = new TNode();
		trainCopy = this.trainZero.getNext();
		TNode busCopy = new TNode();
		busCopy = this.trainZero.getDown();
		TNode walkCopy = new TNode();
		walkCopy = this.trainZero.getDown().getDown();

		while (trainCopy != null){
			trainArray.add(trainCopy.getLocation());
			trainCopy = trainCopy.getNext();
		}
		while (busCopy != null){
			busArray.add(busCopy.getLocation());
			busCopy = busCopy.getNext();
		}

		while (walkCopy != null){
			walkArray.add(walkCopy.getLocation());
			walkCopy = walkCopy.getNext();
		}

		TNode trainFront = new TNode(0);
		TNode busFront = new TNode(0);
		TNode walkFront = new TNode(0);
		newTrainZero = trainFront;
		TNode trainZeroPTR = new TNode(0);
		trainZeroPTR = trainFront;
		TNode busZero = new TNode();
		busZero = busFront;
		TNode walkZero = new TNode();
		walkZero = walkFront;
		trainFront.setDown(busFront);
		busFront.setDown(walkFront);
		 

		for (int i = 0; i < trainArray.size(); i++){
			
			TNode n = new TNode (trainArray.get(i));
			trainFront.setNext(n);
			trainFront = n;
			
			
		}
		for (int i = 1; i < busArray.size(); i++){
			
			TNode n = new TNode (busArray.get(i));
			busFront.setNext(n);
			busFront = n;
			if (busFront.getLocation() == trainZeroPTR.getNext().getLocation()){
				trainZeroPTR.getNext().setDown(busFront);
				if(trainZeroPTR.getNext().getNext() != null){
				trainZeroPTR = trainZeroPTR.getNext();
				}
			}
			
			
		}
		for (int i = 1; i < walkArray.size(); i++){
			TNode n = new TNode (walkArray.get(i));
			walkFront.setNext(n);
			walkFront = n;

			if (walkFront.getLocation() == busZero.getNext().getLocation()){
				busZero.getNext().setDown(walkFront);
				if(busZero.getNext().getNext() != null){
				busZero = busZero.getNext();
				}
			}
		}


	    return newTrainZero;
	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public void addScooter(int[] scooterStops) {

	    // UPDATE THIS METHOD
		TNode busZero = new TNode();
		TNode scooterZero = new TNode();
		TNode walkZero = new TNode();
		busZero = trainZero.getDown();
		walkZero = busZero.getDown();
		scooterZero.setDown(walkZero);
		busZero.setDown(scooterZero);

		TNode busFront = new TNode();
		TNode scooterFront = new TNode();
		TNode walkFront = new TNode();
		
		busFront = busZero;
		scooterFront = scooterZero;
		walkFront = walkZero;
		
		TNode ptrSlow = new TNode();
		TNode ptrFast = new TNode();
		ptrSlow = scooterZero;
		ptrFast = scooterZero;

		for (int i = 0; i < scooterStops.length; i++){
			
			TNode n = new TNode (scooterStops[i]);
			scooterFront.setNext(n);
			scooterFront = n;

			if (scooterFront.getLocation() == busZero.getNext().getLocation()){
				busZero.getNext().setDown(scooterFront);
				if(busZero.getNext().getNext() != null){
				busZero = busZero.getNext();
				}
			}
		}
		scooterFront = scooterZero;
		while (walkFront != null){
			if (walkFront.getLocation() == scooterFront.getNext().getLocation()){
				scooterFront.getNext().setDown(walkFront);
				if(scooterFront.getNext().getNext() != null){
				scooterFront = scooterFront.getNext();
				}
			}
			walkFront = walkFront.getNext();
		}
	}

	/**
	 * Used by the driver to display the layered linked list. 
	 * DO NOT edit.
	 */
	public void printList() {
		// Traverse the starts of the layers, then the layers within
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// Output the location, then prepare for the arrow to the next
				StdOut.print(horizPtr.getLocation());
				if (horizPtr.getNext() == null) break;
				
				// Spacing is determined by the numbers in the walking layer
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print("--");
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print("-");
				}
				StdOut.print("->");
			}

			// Prepare for vertical lines
			if (vertPtr.getDown() == null) break;
			StdOut.println();
			
			TNode downPtr = vertPtr.getDown();
			// Reset horizPtr, and output a | under each number
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				while (downPtr.getLocation() < horizPtr.getLocation()) downPtr = downPtr.getNext();
				if (downPtr.getLocation() == horizPtr.getLocation() && horizPtr.getDown() == downPtr) StdOut.print("|");
				else StdOut.print(" ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
	
	/**
	 * Used by the driver to display best path. 
	 * DO NOT edit.
	 */
	public void printBestPath(int destination) {
		ArrayList<TNode> path = bestPath(destination);
		for (TNode vertPtr = trainZero; vertPtr != null; vertPtr = vertPtr.getDown()) {
			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the number if this node is in the path, otherwise spaces
				if (path.contains(horizPtr)) StdOut.print(horizPtr.getLocation());
				else {
					int numLen = String.valueOf(horizPtr.getLocation()).length();
					for (int i = 0; i < numLen; i++) StdOut.print(" ");
				}
				if (horizPtr.getNext() == null) break;
				
				// ONLY print the edge if both ends are in the path, otherwise spaces
				String separator = (path.contains(horizPtr) && path.contains(horizPtr.getNext())) ? ">" : " ";
				for (int i = horizPtr.getLocation()+1; i < horizPtr.getNext().getLocation(); i++) {
					StdOut.print(separator + separator);
					
					int numLen = String.valueOf(i).length();
					for (int j = 0; j < numLen; j++) StdOut.print(separator);
				}

				StdOut.print(separator + separator);
			}
			
			if (vertPtr.getDown() == null) break;
			StdOut.println();

			for (TNode horizPtr = vertPtr; horizPtr != null; horizPtr = horizPtr.getNext()) {
				// ONLY print the vertical edge if both ends are in the path, otherwise space
				StdOut.print((path.contains(horizPtr) && path.contains(horizPtr.getDown())) ? "V" : " ");
				int numLen = String.valueOf(horizPtr.getLocation()).length();
				for (int j = 0; j < numLen-1; j++) StdOut.print(" ");
				
				if (horizPtr.getNext() == null) break;
				
				for (int i = horizPtr.getLocation()+1; i <= horizPtr.getNext().getLocation(); i++) {
					StdOut.print("  ");

					if (i != horizPtr.getNext().getLocation()) {
						numLen = String.valueOf(i).length();
						for (int j = 0; j < numLen; j++) StdOut.print(" ");
					}
				}
			}
			StdOut.println();
		}
		StdOut.println();
	}
}
