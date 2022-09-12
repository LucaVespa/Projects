package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
       
        StdIn.setFile("purchaseproduct.in");
        StdOut.setFile("purchaseproduct.out");

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
        
        else if(type.equals("purchase")){
            int day = StdIn.readInt();
            int id = StdIn.readInt();
            int amount = StdIn.readInt();
            warehouse.purchaseProduct(id, day, amount);
        }
        
    }
    StdOut.println(warehouse);
    }
}
