package warehouse;

/*
 * Use this class to test the deleteProduct method.
 */ 
public class DeleteProduct {
    public static void main(String[] args) {
        StdIn.setFile("deleteproduct.in");
        StdOut.setFile("deleteproduct.out");

	// Use this file to test addProduct
    int numOfItems = StdIn.readInt();
    Warehouse warehouse = new Warehouse();

    for (int i = 0; i < numOfItems; i++){
        String type = StdIn.readString();
        if(type.equals("add")){
        int day = StdIn.readInt();
        int id = StdIn.readInt();
        String name = StdIn.readString();
        int stock = StdIn.readInt();
        int demand = StdIn.readInt();
        warehouse.addProduct(id, name, stock, day, demand);
        }
        
        else if(type.equals("delete")){
            int id = StdIn.readInt();
            warehouse.deleteProduct(id);
        }
    }
    StdOut.println(warehouse);
	// Use this file to test deleteProduct


    }
}
