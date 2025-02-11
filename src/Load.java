import java.util.ArrayList;


public class Load {
    /**
     * The Load class handles loading products into the vending machine slots.
     */
    public int fill(ArrayList<Product> slotList, String[] productList, String[] args) {
        /**
         * Fills GMM slots with products based on the provided product list.
         * @param slotList The ArrayList representing GMM slots.
         * @param productList The array containing product information.
         * @param args Command-line arguments for file input/output.
         * @return 1 if products are successfully loaded, -1 otherwise.
         */

        int slotNumber = 0;

        // Iterate through each line in the product list
        for (String line : productList) {
            boolean isAvailable = false;
            boolean isFull = true;

            //Take product details off the line
            String[] parts = line.split("\t");
            String productName = parts[0];
            float productPrice = Float.parseFloat(parts[1]);
            String[] smallParts = parts[2].split(" ");
            float productProtein = Float.parseFloat(smallParts[0]);
            float productCarbohydrate = Float.parseFloat(smallParts[1]);
            float productFat = Float.parseFloat(smallParts[2]);

            // If slotList is empty, add the product
            if(slotList.size() == 0){
                Product product1 = new Product(productName, productPrice, productProtein, productCarbohydrate, productFat, 0.0F, slotNumber, 1);
                slotList.add(product1);
                continue;
            }

            // Determine if the product is already present in the slotList.
            for(Product existingProduct : slotList){
                if(existingProduct.getName().equals(productName)){
                }
                if(existingProduct.getName().equals(productName) && !existingProduct.isFull()){
                    isAvailable = true;
                    existingProduct.setCount(existingProduct.getCount()+1);
                    // If the count reaches 10, mark the slot as full
                    if(existingProduct.getCount() == 10) {
                        existingProduct.setFull(true);
                        break;
                    }
                }
            }

            // If the product is not available, check if there's space to add a new product
            if(!isAvailable){
                if(slotList.size() < 24) {
                    slotNumber = slotNumber + 1;
                    Product product = new Product(productName, productPrice, productProtein, productCarbohydrate, productFat, 0.0F, slotNumber, 1);
                    slotList.add(product);
                }else {
                    // Write an informational message to the log file if there is no available slots in GMM.
                    for (Product existingProduct : slotList){
                        if(existingProduct.getCount() != 10){
                            isFull = false ;
                            FileOutput.writeToFile(args[2],"INFO: There is no available place to put " + productName, true, true);
                            break;
                        }
                    }if(isFull){
                        // Write an informational message to the log file if all available slots are taken.
                        FileOutput.writeToFile(args[2],"INFO: There is no available place to put " + productName, true, true);
                        FileOutput.writeToFile(args[2],"INFO: The machine is full!", true, true);
                        return -1;
                    }
                }
            }
        }
        return 1;
    }
}