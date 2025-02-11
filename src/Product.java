public class Product {
    /**
     * The Product class represents a product in a vending machine.
     * It encapsulates information about the product such as its name, price, nutritional values,
     * availability in the slot, and count.
     */
    private int slotNumber;
    private String name;
    private float price;
    private float protein;
    private float carbohydrate;
    private float fat;
    private float calori;
    private int count;
    private boolean isFull;

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public float getProtein() {
        return protein;
    }
    public float getCarbohydrate() {
        return carbohydrate;
    }
    public float getFat() {
        return fat;
    }
    public float getCalori() {
        return calori;
    }
    public int getCount() {
        return count;
    }

    public boolean isFull() { return isFull; }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setCarbohydrate(float carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public void setCalori(float calori) {
        this.calori = calori;
    }

    public void setFull(boolean full) { isFull = full; }

    public Product(String name, float price, float protein, float carbohydrate, float fat, float calori, int slotNumber, int count){
        /**
         * Constructs a new Product object with the given parameters.
         * @param name The name of the product.
         * @param price The price of the product.
         * @param protein The protein of the product.
         * @param carbohydrate The carbohydrate of the product.
         * @param fat The fat of the product.
         * @param calori The calorie of the product.
         * @param slotNumber The slot number of the product.
         * @param count The quantity of the product available.
         */
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.slotNumber = slotNumber;
        calori = calculateCalori(protein, carbohydrate, fat);
        this.count = count;
    }


    public float calculateCalori(float protein, float carbohydrate, float fat){
        /**
         * Calculates the calorie of the product based on its nutritional values.
         * @param protein The protein of the product.
         * @param carbohydrate The carbohydrate of the product.
         * @param fat The fat of the product.
         * @return The calorie of the product.
         */
        calori = 4 * protein + 4 * carbohydrate + 9 * fat;
        return calori;
    }

}
