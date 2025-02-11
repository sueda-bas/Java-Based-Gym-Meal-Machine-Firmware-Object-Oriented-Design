import java.util.ArrayList;

public class Purchase {
    /**
     * The Purchase class handles the purchasing process in the vending machine.
     */
    public void buy (String[] purchaseList, ArrayList<Product>slotList, String[] args){
        /**
         * Processes purchases based on the provided purchase list and updates GMM state.
         * @param purchaseList The array containing purchase information.
         * @param slotList The ArrayList representing GMM slots.
         * @param args Command-line arguments for file input/output.
         */
        for (String line : purchaseList){
            FileOutput.writeToFile(args[2],"INPUT: " + line  , true, true);
            //Take purchase details off the line
            String[] parts = line.split("\t");
            String[] money = parts[1].split(" ");
            String preference = parts[2];
            String stringValue = parts[3];
            int value = Integer.parseInt(stringValue);
            boolean isValidPrice = true;
            boolean isEmptySlot = false;
            boolean isProductExist = false;
            int givenMoney = 0;

            // Iterate through each element of the money array and check if the money is valid for GMM
            for (String i : money) {
                int j = Integer.parseInt(i);
                if (j != 1 && j != 5 && j != 10 && j != 20 && j != 50 && j != 100 && j != 200) {
                    //If there is invalid money give an information message
                    FileOutput.writeToFile(args[2], "INFO: Invalid money, try again with 1, 5, 10, 20, 50, 100, or 200 TL.", true, true);
                    continue;
                }else{isValidPrice = true;}
                givenMoney += j;
            }
            //If it is not valid return the money
            if(!isValidPrice){
                for (String i : money) {
                    int j = Integer.parseInt(i);
                    givenMoney += j;
                    returnChange(givenMoney, 0, args);
                }
            }

            // Process the purchase based on the preference
            switch(preference){
                case "NUMBER":
                    // Handle purchases based on slot number
                    if(value < 0 || value > 23){
                        //If slot number is invalid give an information message
                        FileOutput.writeToFile(args[2],"INFO: Number cannot be accepted. Please try again with another number.", true, true);
                        returnChange(givenMoney, 0, args);
                        break;
                    }else if(value <= 23 && value > slotList.size()){
                        //Check if the slot is empty and give an information message
                        FileOutput.writeToFile(args[2],"INFO: This slot is empty, your money will be returned.", true, true);
                        returnChange(givenMoney, 0, args);
                        break;
                    }else if(value >= 0 && value < slotList.size()) {
                        //Check if the nutritional value is valid
                        float productPrice = slotList.get(value).getPrice();
                        String productName = slotList.get(value).getName();
                        int countOfProducts = slotList.get(value).getCount();

                        //Check if the product is finished
                        if (countOfProducts == 0) {
                            isEmptySlot = true;
                            FileOutput.writeToFile(args[2], "INFO: This slot is empty, your money will be returned.", true, true);
                            returnChange(givenMoney, 0, args);
                            break;
                        }
                        if (givenMoney < productPrice) {
                            //Check if the money is sufficient
                            FileOutput.writeToFile(args[2], "INFO: Insufficient money, try again with more money.", true, true);
                            returnChange(givenMoney, 0, args);
                            break;
                        }
                        //If there is no error in purchase sell the product
                        if (givenMoney >= productPrice && !isEmptySlot) {
                            FileOutput.writeToFile(args[2], "PURCHASE: You have bought one " + productName, true, true);
                            returnChange(givenMoney, productPrice, args);
                            if (slotList.get(value).getCount() != 0) {
                                slotList.get(value).setCount(slotList.get(value).getCount() - 1);
                            }
                            if (slotList.get(value).getCount() == 0) {
                                slotList.get(value).setName("___");
                                slotList.get(value).setFat(-6);
                                slotList.get(value).setProtein(-6);
                                slotList.get(value).setCarbohydrate(-6);
                                slotList.get(value).setCalori(-6);
                            }
                            break;
                        }
                    }
                case "PROTEIN":
                    // Handle purchases based on protein
                    for( Product product : slotList){
                        //Search the required product
                        if(value - 5 <= product.getProtein() && product.getProtein() <= value + 5) {
                            isProductExist = true;
                            float productPrice = product.getPrice();
                            if (givenMoney < productPrice) {
                                //Check if the money is sufficient
                                FileOutput.writeToFile(args[2], "INFO: Insufficient money, try again with more money.", true, true);
                                returnChange(givenMoney, 0, args);
                                break;
                            } else {
                                FileOutput.writeToFile(args[2], "PURCHASE: You have bought one " + product.getName(), true, true);
                                returnChange(givenMoney, productPrice, args);
                                if (product.getCount() != 0){
                                    product.setCount(product.getCount() - 1);}
                                //If product is finished in the slot update the product information
                                if(product.getCount() == 0){
                                    product.setName("___");
                                    product.setFat(-6);
                                    product.setProtein(-6);
                                    product.setCarbohydrate(-6);
                                    product.setCalori(-6);
                                }
                                break;
                            }
                        }
                    }if(!isProductExist){
                    FileOutput.writeToFile(args[2],"INFO: Product not found, your money will be returned."  , true, true);
                    returnChange(givenMoney, 0, args);
                    break;
                }break;

                case "CARB":
                    for( Product product : slotList){
                        //Search the required product
                        if(value - 5 <= product.getCarbohydrate() && product.getCarbohydrate() <= value + 5) {
                            isProductExist = true;
                            float productPrice = product.getPrice();
                            if (givenMoney < productPrice) {
                                //Check if the money is sufficient
                                FileOutput.writeToFile(args[2], "INFO: Insufficient money, try again with more money.", true, true);
                                returnChange(givenMoney, 0, args);
                                break;
                            } else {
                                FileOutput.writeToFile(args[2], "PURCHASE: You have bought one " + product.getName(), true, true);
                                returnChange(givenMoney, productPrice, args);
                                if (product.getCount() != 0){
                                    product.setCount(product.getCount() - 1);}
                                //If product is finished in the slot update the product information
                                if(product.getCount() == 0){
                                    product.setName("___");
                                    product.setFat(-6);
                                    product.setProtein(-6);
                                    product.setCarbohydrate(-6);
                                    product.setCalori(-6);

                                }
                                break;

                            }
                        }
                    }if(!isProductExist){
                    FileOutput.writeToFile(args[2],"INFO: Product not found, your money will be returned."  , true, true);
                    returnChange(givenMoney, 0, args);
                    break;
                }break;

                case "FAT":
                    for( Product product : slotList){
                        //Search the required product
                        if(value - 5 <= product.getFat() && product.getFat() <= value + 5) {
                            isProductExist = true;
                            float productPrice = product.getPrice();
                            if (givenMoney < productPrice) {
                                //Check if the money is sufficient
                                FileOutput.writeToFile(args[2], "INFO: Insufficient money, try again with more money.", true, true);
                                returnChange(givenMoney, 0, args);
                                break;
                            } else {
                                FileOutput.writeToFile(args[2], "PURCHASE: You have bought one " + product.getName(), true, true);
                                returnChange(givenMoney, productPrice, args);
                                if (product.getCount() != 0){
                                    product.setCount(product.getCount() - 1);}
                                //If product is finished in the slot update the product information
                                if(product.getCount() == 0){
                                    product.setName("___");
                                    product.setFat(-6);
                                    product.setProtein(-6);
                                    product.setCarbohydrate(-6);
                                    product.setCalori(-6);

                                }
                                break;
                            }
                        }
                    }if(!isProductExist){
                    FileOutput.writeToFile(args[2],"INFO: Product not found, your money will be returned."  , true, true);
                    returnChange(givenMoney, 0, args);
                    break;
                }break;

                case "CALORIE":
                    for( Product product : slotList){
                        //Search the required product
                        if(value - 5 <= product.getCalori() && product.getCalori() <= value + 5) {
                            isProductExist = true;
                            float productPrice = product.getPrice();
                            if (givenMoney < productPrice) {
                                //Check if the money is sufficient
                                FileOutput.writeToFile(args[2], "INFO: Insufficient money, try again with more money.", true, true);
                                returnChange(givenMoney, 0, args);
                                break;
                            } else {
                                FileOutput.writeToFile(args[2], "PURCHASE: You have bought one " + product.getName(), true, true);
                                returnChange(givenMoney, productPrice, args);
                                if (product.getCount() != 0){
                                    product.setCount(product.getCount() - 1);}
                                //If product is finished in the slot update the product information
                                if(product.getCount() == 0){
                                    product.setName("___");
                                    product.setFat(-6);
                                    product.setProtein(-6);
                                    product.setCarbohydrate(-6);
                                    product.setCalori(-6);

                                }
                                break;
                            }
                        }
                    }if(!isProductExist){
                    FileOutput.writeToFile(args[2],"INFO: Product not found, your money will be returned."  , true, true);
                    returnChange(givenMoney, 0, args);
                    break;
                }break;
            }
        }
    }
    public void returnChange(float givenMoney, float price, String[] args){
        /**
         * Calculates and returns the change to be given back to the user.
         * @param givenMoney The money given by the user.
         * @param price The price of the product purchased.
         * @param args Command-line arguments for file input/output.
         */
        float floatChange = givenMoney - price;
        int change = (int) floatChange;
        FileOutput.writeToFile(args[2],"RETURN: Returning your change: " + change + " TL", true, true);
    }

    public void giveOutputOfGmm ( ArrayList<Product> slotList ,String[] args ){
        /**
         * Outputs the current state of the Gym Meal Machine, including occupied slots with products and empty slots.
         * @param slotList The ArrayList representing the vending machine slots.
         * @param args Command-line arguments for file output.
         */
        int emptySlot = 24 - slotList.size();
        int upSlots = emptySlot % 4;
        FileOutput.writeToFile(args[2],"-----Gym Meal Machine-----", true, true);
        for(Product slot : slotList){
            if(slot.getSlotNumber()%4==3){
                if(slot.getCalori() < 0){slot.setCalori(0);}
                FileOutput.writeToFile(args[2],slot.getName() + "(" + Math.round(slot.getCalori())  + ", " + slot.getCount()  + ")___", true, true);
            }else {
                if(slot.getCalori() < 0){slot.setCalori(0);}
                FileOutput.writeToFile(args[2], slot.getName() + "(" + Math.round(slot.getCalori()) + ", " + slot.getCount() + ")___", true, false);
            }
        }
        if(emptySlot > 0) {
            for (int i = 1; i < upSlots; i++) {
                FileOutput.writeToFile(args[2], "___(0, 0)___", true, false);
            }
            for (int i = 0; i <= emptySlot - upSlots; i++) {
                if (i % 4 == 0) {
                    FileOutput.writeToFile(args[2], "___(0, 0)___", true, true);
                } else {
                    FileOutput.writeToFile(args[2], "___(0, 0)___", true, false);
                }
            }
        }
        FileOutput.writeToFile(args[2],"----------", true, true);
    }
}




