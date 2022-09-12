package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile("betteraddproduct.in");
        StdOut.setFile("betteraddproduct.out");

	// Use this file to test addProduct
    int numOfItems = StdIn.readInt();
    Warehouse warehouse = new Warehouse();

    for (int i = 0; i < numOfItems; i++){
        int day = StdIn.readInt();
        int id = StdIn.readInt();
        String name = StdIn.readString();
        int stock = StdIn.readInt();
        int demand = StdIn.readInt();

        warehouse.betterAddProduct(id, name, stock, day, demand);
    }
    StdOut.println(warehouse);
        
        // Use this file to test betterAddProduct
    }
}
