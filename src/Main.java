import java.util.ArrayList;


public class Main {
    /**
     * The Main class contains the main method to execute the GMM.
     */
    public static void main(String[] args) {

        Load load = new Load();
        Purchase purchase = new Purchase();

        String[] productList = FileInput.readFile(args[0], true);
        String[] purchaseList = FileInput.readFile(args[1], true);

        // Create an ArrayList to store Product objects
        ArrayList<Product> slotList = new ArrayList<Product>();

        // Load products into the vending machine slots
        load.fill(slotList, productList, args);

        // Show initial state of the vending machine
        purchase.giveOutputOfGmm(slotList, args);

        // Process purchases and update GMM
        purchase.buy(purchaseList, slotList, args);

        // Show updated state of GMM after purchases
        purchase.giveOutputOfGmm(slotList, args);

    }
}
