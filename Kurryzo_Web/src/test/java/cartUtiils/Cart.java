package cartUtiils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    // A map to store items: key = (name + size), value = MenuItem
   // private Map<String, MenuItem> cartItems = new HashMap<>(); 
	
	// Use LinkedHashMap to preserve the insertion order of items
    private Map<String, MenuItem> cartItems = new LinkedHashMap<>();
    private double expPackingcost;
    private double expPackingcosttax;
    private double expItemtaxtotal;
    private double expTotaltax;
    private double expToPay;
    private String expOrderDate;
    private String expOrderNo;
    private String expDeliveryAddress;
    private double expDeliverCharge;

    // Add or update an item in the cart
    public void addOrUpdateItem(MenuItem newItem) 
    {
        String key = newItem.getName() + "-" + newItem.getSize(); // Unique key based on name and size

        if (cartItems.containsKey(key)) 
        {
            // Update quantity and total if item exists
            cartItems.get(key).updateQtyAndTotal(newItem.getQty());
        } else {
            // Add the new item to the cart
            cartItems.put(key, newItem);
        }
    }

    // Get the full detailed output for all items in the cart
    public String getDetailedCartOutput()  
    {
        StringBuilder detailedOutput = new StringBuilder();
        for (MenuItem item : cartItems.values()) {
            detailedOutput.append(item.getDetailedOutput()).append("\n\n");
        }
        return detailedOutput.toString();
    }

    // Get the summary output for all items in the cart
    public String getSummaryCartOutput() {
        StringBuilder summaryOutput = new StringBuilder();
        double totalCartPrice = 0;
        int totalItemCount = 0; 
        for (MenuItem item : cartItems.values()) 
        {
            summaryOutput.append("Item = ").append(item.getName()).append("\n")
                         .append("size = ").append(item.getSize()).append("\n")
                         .append("Qty = ").append(item.getQty()).append("\n")
                         .append("Total = ₹").append(item.getTotalPrice()).append("\n\n");
            totalCartPrice += item.getTotalPrice();
        }

        summaryOutput.append("Total = ₹").append(totalCartPrice);
        return summaryOutput.toString();
    }
    /* 
    public double getCartTotal() 
    {
      
        double totalCartPrice = 0;

        for (MenuItem item : cartItems.values()) 
        {
            
          totalCartPrice += item.getTotalPrice();
            
        }
         return totalCartPrice;
    }*/
   //Alter native approach for getCartTotal1()
   
    public double getCartTotal() 
    {
        return cartItems.values().stream().mapToDouble(MenuItem::getTotalPrice).sum();
    }

    // Get the distinct item count (number of different items in the cart)
    public int getCartItemCount()
    {
  
    	System.out.println("-----------------");
    	System.out.println(cartItems.size());
    	return cartItems.size();
        
    }
    

    // Get total item count (sum of all quantities in the cart)
    public int getTotalItemCount() 
    {
        return cartItems.values().stream().mapToInt(MenuItem::getQty).sum();
    }
/*
    // Get the total item count (sum of all quantities)
    public int getTotalItemCount() {
        int totalItemCount = 0;
        for (MenuItem item : cartItems.values()) {
            totalItemCount += item.getQty();
        }
        return totalItemCount;
    }*/

    // New method to return all MenuItem objects in the cart
    public Collection<MenuItem> getCartItems() 
    {
        return cartItems.values();
    }
    
 // Getters and Setters
    
    public double getExpPackingcost() {
        return expPackingcost;
    }

    public void setExpPackingcost(double expPackingcost) 
    {
        this.expPackingcost = expPackingcost;
    }

    public double getExpPackingcosttax() {
        return expPackingcosttax;
    }

    public void setExpPackingcosttax(double expPackingcosttax) 
    {
        this.expPackingcosttax = expPackingcosttax;
    }

    public double getExpItemtaxtotal() 
    {
        return expItemtaxtotal;
    }

    public void setExpItemtaxtotal(double expItemtaxtotal) 
    {
        this.expItemtaxtotal = expItemtaxtotal;
    }

    public double getExpTotaltax() {
        return expTotaltax;
    }

    public void setExpTotaltax(double expTotaltax) {
        this.expTotaltax = expTotaltax;
    }

    public double getExpToPay() {
        return expToPay;
    }

    public void setExpToPay(double expToPay) {
        this.expToPay = expToPay;
    }
    
    //Summary Getter and Setters
    public String  getExpOrderDate()
    {
        return expOrderDate;
    }

    public void setExpOrderDate(String expOrderDate) 
    {
        this.expOrderDate = expOrderDate;
    }
    
    public String  getExpOrderNo()
    {
        return expOrderNo;
    }

    public void setExpOrderNo(String expOrderNo) 
    {
        this.expOrderNo = expOrderNo;
    }
    
    public String getExpDeliveryAddress()
    {
        return expDeliveryAddress;
    }

    public void setExpDeliveryAddress(String expDeliveryAddress) 
    {
        this.expDeliveryAddress = expDeliveryAddress;
    }
    
    public double  getExpDeliverCharge()
    {
        return expDeliverCharge;
    }

    public void setExpDeliverCharge(double expDeliverCharge) 
    {
        this.expDeliverCharge = expDeliverCharge;
    }
}


