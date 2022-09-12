package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {
        StdIn.setFile("fixheap.in");
        StdOut.setFile("fixheap.out");

	// Use this file to test addProduct
    int numOfItems = StdIn.readInt();
    Warehouse warehouse = new Warehouse();

    for (int i = 0; i < numOfItems; i++){
        int day = StdIn.readInt();
        int id = StdIn.readInt();
        String name = StdIn.readString();
        int stock = StdIn.readInt();
        int demand = StdIn.readInt();

        warehouse.addProduct(id, name, stock, day, demand);
    }
    StdOut.println(warehouse);
    }
}
