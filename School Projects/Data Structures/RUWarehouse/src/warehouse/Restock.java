package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile("restock.in");
        StdOut.setFile("restock.out");

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
        else if(type.equals("restock")){
            int id = StdIn.readInt();
            int amount = StdIn.readInt();
            warehouse.restockProduct(id, amount);
        }
        
    }
    StdOut.println(warehouse);
    }
}
