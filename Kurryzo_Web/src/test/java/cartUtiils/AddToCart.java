package cartUtiils;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import actions.MenuPageActions;
import pageObjects.MenuPage;
import utils.WaitUtils;

public class AddToCart {
   // private WebDriver driver;
    private MenuPage menuPage;
	private MenuPageActions menuPageActions;
	//private JSONObject objDailyMenu;
	private WaitUtils waitUtils;
	
    // Constructor
    public AddToCart() throws IOException 
    {
    	   
    	    
    	   // waitUtils = new WaitUtils(driver);  // Initialize WaitUtils
    	   // objDailyMenu=ReadJson.setJson(Constant.Path_jsonTestData,Constant.File_Testdata_DailyMenu);
    	    
    }

    // Method to read the JSON data and return the menu items for the given store
    public List<MenuItem> getMenuItems(JSONObject menuDetails, String storeName) 
    {
        List<MenuItem> menuItems = new ArrayList<>();
        JSONArray storeMenu = menuDetails.getJSONArray(storeName);

        for (int i = 0; i < storeMenu.length(); i++) 
        {
            JSONObject item = storeMenu.getJSONObject(i);
            String name = item.getString("name");
            String category = item.getString("category");
            double price = item.getDouble("price");
            int qty = Integer.parseInt(item.getString("qty"));
            String size = item.getString("size");
            int tax = item.getInt("tax");
            String servesFor = item.getString("servesFor");
            double defaultPrice = item.getDouble("defaultPrice");
            boolean multiServing= item.getBoolean("multiServingItem");

            // Create a MenuItem object and add it to the list
            MenuItem menuItem = new MenuItem(name, category, defaultPrice, price, qty, size, tax, servesFor,multiServing);
            menuItems.add(menuItem);
        }

        return menuItems;
    }

/*
    public void addItemToCart(String expItemName, String expCategory, double expDefaultPrice, String expServingSize,
    		String expServesFor, double expPrice,  int expQty) throws InterruptedException 
    {
    	 // Step 1: Search for menu item
    		menuPageActions.searchMenuItem(expItemName);
    	        // Step 2: Find the menu row and loop through it
    	        List<WebElement> searchRows = menuPage.getTotalRows();
    	        int count = searchRows.size();
    	        //int qty = Integer.parseInt(expQty);
    	        int qty = expQty;

    	        for (int i = 0; i < count; i++) {
    	            //waitUtils.waitForElementVisibleBy(menuPage.getItemByName(expItemName), 10);
    	            WebElement item =menuPage.getItemByName(expItemName);
    	            System.out.println("Search item"+item.getText());
    	            if (item != null) {
    	                // Step 3: Verify the price
    	                WebElement priceElement=menuPage.getPriceElementByIndex(i + 1);
    	                String actualPrice = priceElement.getText().substring(1);
    	                
    	                Assert.assertEquals( FormatUtils.formatPrice(expDefaultPrice),actualPrice);
    	              
    	                
    	                // Step 4: Click the add button
    	                for (int j = 1; j <= qty; j++) 
    	                {
    	                	WebElement addButton = (menuPage.getAddToCartButton(i + 1));
    	                   menuPageActions.clickElementWithRetry(addButton);
    	                // Assuming modalPopup is already located as a WebElement
    	                   WebElement modalPopup = menuPage.getModalPopup(); // This is your WebElement
    	                   
    	                   // Wait for visibility and assign it to modalPopup1
    	                   WebElement modalPopup1 = waitUtils.waitForVisibility(modalPopup, 5); // Wait for up to 10 seconds
    	                   
    	                   
    	                   if (modalPopup1 != null) 
    	                   {
    	                	   Thread.sleep(2000);
    	            
    	                       WebElement popitemname = menuPage.getModalPopupItemName(expItemName);
    	                       Assert.assertEquals(expItemName, popitemname.getText());
    	                    
    	                   // menuPageActions.handleModalActions(modalPopup);
    	                    WebElement servingSizeElement = menuPage.getServingSizeElement(expServingSize);
    	                    Assert.assertEquals(expServingSize, servingSizeElement.getText());
    	                    
    	                    WebElement actServedFor = menuPage.getServedForElement(expServingSize);
    	                   
    	                     Assert.assertEquals(expServesFor, actServedFor.getText());
    	                    
    	                    WebElement actPrice = menuPage.getPriceElementByServingSize(expServingSize);
    	                    Assert.assertEquals(FormatUtils.formatPrice(expPrice),actPrice.getText().substring(1));
    	                    WebElement radioButton = menuPage.getRadioButton(expServingSize);
    	                    menuPageActions.clickElementWithRetry(radioButton);

    	                    // Step 6: Click the Add button in modal
    	                    WebElement addItemButton = menuPage.getAddButton();
    	                    menuPageActions.clickElementWithRetry(addItemButton);

    	                    // Step 7: Verify cart items
    	                   //verifyCartItems(expItemName, expServingSize, expDefaultPrice, expPrice, expServedFor, expQty, expCategory, expTotalQty, expRowCount, expTax);
    	                    
    	                    // Sleep for stability
    	                    Thread.sleep(1000);
    	                    }
    	                }
    	                
    	            }
    	        }}
/*
    // Method to interact with the web page and add the item to the cart
    public void addItemToCart1(MenuItem menuItem) 
    {
        // Find the "Add to Cart" button using the name of the item
        WebElement addItemButton = driver.findElement(By.xpath("//button[contains(text(),'" + menuItem.getName() + "')]"));
        addItemButton.click();
      
        // Input the quantity
        WebElement qtyInput = driver.findElement(By.xpath("//input[@name='quantity']"));
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(menuItem.getQty()));

    }
    */
	/*
    // Optional: Method to validate the cart (you could compare expected items with actual cart content)
    public void validateCart(List<MenuItem> expectedItems)
    {
        // Implement cart validation logic here
    }*/
}
