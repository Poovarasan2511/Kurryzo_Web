package cartUtiils;



public class MenuItem {
    private String name;
    private String category;
    private double defaultPrice;
    private double price;
    private int qty;
    private String size;
    private double totalPrice;
    private int tax;
    private String servesFor;
    private double itemTax;
    private double itemDiscount;
    boolean multiServing;
    // Constructor to initialize all fields
    public MenuItem(String name, String category, double defaultPrice, double price, int qty, String size, int tax, String servesFor,boolean multiServing) {
        this.name = name;
        this.category = category;
        this.defaultPrice = defaultPrice;
        this.price = price;
        this.qty = qty;
        this.size = size;
        this.tax = tax;
        this.servesFor = servesFor;
        // Calculate total price initially
        this.totalPrice = price * qty;
        this.multiServing=multiServing;
    }

    // Getters
    public String getName() 
    {
        return name;
    }

    public String getCategory() 
    {
        return category;
    }

    public double getDefaultPrice() {
        return defaultPrice;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public String getSize() {
        return size;
    }
    

    public  boolean getMutiSeriving() 
    {
        return multiServing;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTax() {
        return tax;
    }

    public String getServesFor() {
        return servesFor;
    }

    // Setters for qty and totalPrice are needed for updates
    public void setQty(int qty) {
        this.qty = qty;
        // Recalculate total price whenever quantity is updated
        this.totalPrice = this.price * this.qty;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Method to update the quantity and recalculate total price
    public void updateQtyAndTotal(int additionalQty) {
        this.qty += additionalQty;
        this.totalPrice = this.price * this.qty;
    }

    // Method to get detailed output for the item
    public String getDetailedOutput() {
        return "Name: " + name + "\n" +
               "Category: " + category + "\n" +
               "Default Price: ₹" + defaultPrice + "\n" +
               "Size: " + size + "\n" +
               "Price: ₹" + price + "\n" +
               "Serves For: " + servesFor + "\n" +
               "Quantity: " + qty + "\n" +
               "Tax: " + tax + "%\n" +
               "Total Price: ₹" + totalPrice;
    }

    // Method to get summary output for the item
    public String getSummaryOutput() {
        return name + " " + qty + " * " + size + " = ₹" + totalPrice;
    }
    
    // Getters and setters
    public double getItemTax() {
        return itemTax;
    }

    public void setItemTax(double itemTax) {
        this.itemTax = itemTax;
    }

    public double getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }
}
