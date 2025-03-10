package cartUtiils;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utils.FormatUtils;
import utils.TestContextSetup;

public class CommonReusableMethods 
{
    
   // private WebDriver driver;
   // private Cart cart;
    private TestContextSetup testContextSetup;

    public CommonReusableMethods(TestContextSetup testContextSetup)
    {
      //  this.driver = testContextSetup.getDriver();
      //this.cart = cart;
        this.testContextSetup = testContextSetup; 
        
        // Ensure elements are clickable
       // testContextSetup.getAssertionUtils().assertElementClickable(null, null);
    }

    /**
     * Adds multiple menu items to the cart.
     * @param menuItems List of MenuItem objects to add
     * @throws InterruptedException
     */
    public void addItemsToCart(List<MenuItem> menuItems) throws InterruptedException
    {
        for (MenuItem menuItem : menuItems) {
            addItemToCart(menuItem);
        }
    }

    /**
     * Adds a single menu item to the cart.
     * @param menuItem MenuItem object to add
     * @throws InterruptedException
     */
    public void addItemToCart(MenuItem menuItem) throws InterruptedException
    {
        String expItemName = menuItem.getName();
        double expDefaultPrice = menuItem.getDefaultPrice();
        String expServingSize = menuItem.getSize();
        boolean multiServing = menuItem.getMutiSeriving(); // Get multi-serving flag

        // Search for the menu item
        testContextSetup.getMenuPageActions().searchMenuItem(expItemName);

        List<WebElement> searchRows = testContextSetup.getMenuPageActions().getTotalRows();
        for (int i = 0; i < searchRows.size(); i++) 
        {
            WebElement item = testContextSetup.getMenuPageActions().getItemByName(expItemName);
            
            if (item != null) 
            {
                WebElement priceElement = testContextSetup.getMenuPageActions().getPriceElementByIndex(i + 1);
                String actualPrice = priceElement.getText().substring(1); // Remove currency symbol
                testContextSetup.getAssertionUtils().assertTextEquals(actualPrice, FormatUtils.formatPrice(expDefaultPrice));
                
                for (int j = 1; j <= menuItem.getQty(); j++) 
                {
                    WebElement addButton = testContextSetup.getMenuPageActions().getAddToCartButton(i + 1);
                    testContextSetup.getMenuPageActions().clickElementWithRetry(addButton);

                    WebElement modalPopup = testContextSetup.getMenuPageActions().getModalPopup();

                    // Assert if multiServing is true, modalPopup should not be null
                 //   testContextSetup.getAssertionUtils().assertNullOrNotNull(modalPopup, multiServing, "Modal popup visibility mismatch for multi-serving item: " + expItemName);

                    // If modalPopup is not null (only for multi-serving items)
                    if (multiServing && modalPopup != null) {
                        testContextSetup.getWaitUtils().waitForElementVisible(modalPopup, 5);
                        
                        // Assert modal content
                        WebElement popItemName = testContextSetup.getMenuPageActions().getModalPopupItemName(expItemName);
                        testContextSetup.getAssertionUtils().assertTextEquals(popItemName.getText(), expItemName);

                        WebElement servingSizeElement = testContextSetup.getMenuPageActions().getServingSizeElement(expServingSize);
                        testContextSetup.getAssertionUtils().assertTextEquals(servingSizeElement.getText(), expServingSize);

                        WebElement radioButton = testContextSetup.getMenuPageActions().getRadioButton(expServingSize);
                        testContextSetup.getMenuPageActions().clickElementWithRetry(radioButton);

                        WebElement addItemButton = testContextSetup.getMenuPageActions().getAddButton();
                        testContextSetup.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                        testContextSetup.getWaitUtils().waitForElementVisible(addItemButton, 30);
                        Thread.sleep(1000);
                        testContextSetup.getMenuPageActions().clickElementWithRetry(addItemButton);
                        testContextSetup.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    }
                }
            }
        }
        // After adding item to cart, update cart
        testContextSetup.getCart().addOrUpdateItem(menuItem);
    }

    // verifyCartItems` Here in cart (Menu Page)
    
    public void verifyMenuPageCartItems(String expItemName, String expServingSize, double expTotalItemPrice, int expTotalQty,int rowIndex ) throws InterruptedException 
    {
    	
    	String actualItemName=   testContextSetup.getMenuPageActions().getCartItemNameElement(rowIndex);
    	String actuaPrice=testContextSetup.getMenuPageActions().getCartRowPriceElement(rowIndex);
    	String actualservingSize=testContextSetup.getMenuPageActions().getCartRowServingSizeElement(rowIndex);
    	String actualServingTotal=testContextSetup.getMenuPageActions().getCartRowServingTotalPriceElement(rowIndex);
    	String actualTotalQty=testContextSetup.getMenuPageActions().getCarRowTotalQtyElement(rowIndex); 	
    	System.out.println("Actual Item Name: " + actualItemName);
    	System.out.println("Actual Price: " + actuaPrice);
    	System.out.println("Actual Serving Size: " + actualservingSize);
    	System.out.println("Actual Total Quantity: " + actualTotalQty);
      // double expItemTotal = expPrice * expTotalQty;
    	double expItemTotal = expTotalItemPrice;
        String formattedServingSize = expTotalQty + " X " + expServingSize;
        System.out.println("Verifying cart items...");    
       // testContextSetup.getWaitUtils().waitForVisibility(itemNameElement, 10);
        testContextSetup.getAssertionUtils().assertTextEquals(actualItemName,expItemName);
    // Validate Total Price
       double actualPriceValue = Double.parseDouble(actuaPrice.substring(1));
       testContextSetup.getAssertionUtils().assertTextEquals( FormatUtils.formatPrice(actualPriceValue),FormatUtils.formatPrice(expItemTotal));
      //  testContextSetup.getAssertionUtils().assertTextEquals(expItemTotal, priceElement.getText(), "Total price mismatch!");
         // Validate Serving Size
       testContextSetup.getAssertionUtils().assertTextEquals(actualservingSize,formattedServingSize);
    // Validate Serving Size Total Price
         double servingTotal = Double.parseDouble(actualServingTotal.substring(1));
         testContextSetup.getAssertionUtils().assertTextEquals(FormatUtils.formatPrice(servingTotal),FormatUtils.formatPrice(expItemTotal));
    }
    

    /**
     * Verifies the cart total by comparing the expected item count and total amount 
     * displayed on the menu page with the calculated values.
     *
     * @param count    The expected distinct item count.
     * @param subtotal The expected cart subtotal.
     */
    //Veriying in menu page
    public void verifyCartTotal(int count, double subtotal) 
    {
        // Expected text for item count, e.g., "3 ITEMS"
        String expectedItemText = count + " ITEMS";

        // Retrieve actual displayed values from the MenuPage through the TestContext
        String actualItemText = testContextSetup.getMenuPageActions().getTotalCartItemCount();
        String actualTotalText = testContextSetup.getMenuPageActions().getCartSubTotal() ;
        
        // Clean up the total text (remove currency symbols, whitespace, etc.)
        actualTotalText = actualTotalText.replaceAll("[^0-9.]", "").trim();
        String expectedTotalText = FormatUtils.formatPrice(subtotal).trim();

        // Perform assertions
        testContextSetup.getAssertionUtils().assertTextEquals(actualItemText, expectedItemText);
        testContextSetup.getAssertionUtils().assertTextEquals(actualTotalText, expectedTotalText);
    }
    //verify cart line item details in check outpage
    public void verifyCheckOutPageCartItems(String expItemName, String expServingSize, double expTotalItemPrice, int expTotalQty,int rowIndex ) throws InterruptedException 
    {
    	int viewportHeight = testContextSetup.getDriver().manage().window().getSize().getHeight();  // Get the viewport height
    	int scrollAmount = viewportHeight / 2 + 1;  // Scroll more than half a page
    	testContextSetup.browserActions().scrollPage(0, scrollAmount);;
    	String actualItemName=   testContextSetup.getCheckOutPageActions().getCheckoutRowItemName(rowIndex);
    	String actuaPrice=testContextSetup.getCheckOutPageActions().getCheckoutRowPrice(rowIndex);
    	String actualservingSize=testContextSetup.getCheckOutPageActions().getCheckoutRowServingSize(rowIndex);
    	String actualServingTotal=testContextSetup.getCheckOutPageActions().getCheckoutRowServingTotalPrice(rowIndex);
    	double expItemTotal = expTotalItemPrice;
        String formattedServingSize = expTotalQty + " X " + expServingSize;
        System.out.println("Verifying cart items...");    
        testContextSetup.getAssertionUtils().assertTextEquals(actualItemName,expItemName);
    // Validate Total Price
        double actualPriceValue = Double.parseDouble(actuaPrice.substring(1)); 
        testContextSetup.getAssertionUtils().assertTextEquals(FormatUtils.formatPrice(actualPriceValue),FormatUtils.formatPrice(expItemTotal));
         // Validate Serving Size
        Assert.assertEquals(formattedServingSize, actualservingSize, "Serving size mismatch!");
        // Validate Serving Size Total Price
        double servingTotal = Double.parseDouble(actualServingTotal.substring(1));
        testContextSetup.getAssertionUtils().assertTextEquals(FormatUtils.formatPrice(servingTotal),FormatUtils.formatPrice(expItemTotal));
        // Validate Total Quantity       
    }
    
     

public void SelectDeliveryAddress(String address) throws InterruptedException {
    // Normalize the expected address
    String expDeliveryaddress = address.replaceAll("\\s+", " ").trim();
    int count = testContextSetup.getCheckOutPageActions().geTtotalNoDeliveryAddress();
    boolean addressFound = false; // Flag to track if the address was found
    
    for (int i = 1; i <= count; i++) {
        String actDelAddress = testContextSetup.getCheckOutPageActions().getAllDelevieryAddress(i);
        
        // Normalize the actual address by removing extra spaces and trimming
        String normalizedActDelAddress = actDelAddress.replaceAll("\\s+", " ").trim();
        // Compare the two normalized addresses
        if (expDeliveryaddress.equals(normalizedActDelAddress))
        {
            System.out.println("----------- code coming to conditions ");
            WebElement buttonElement = testContextSetup.getCheckOutPageActions().selectDeiveryAddress(i);
            testContextSetup.browserActions().clickUsingJavaScript(buttonElement);
            addressFound = true; // Mark as found
            break; // Exit loop once the address is found and clicked
        }
    }

    // Assertion to ensure the address is found
    Assert.assertTrue(addressFound,"The expected delivery address was not found.");
    Thread.sleep(1000);
}
    
 
    //Find packing cost value
    public double calculatePackingcost(double expectedSubTotal,double packingcostPercent )
    {
    	double expPackingcost=(expectedSubTotal*packingcostPercent)/100;
    	return expPackingcost; 	
    }
    
    //Find tax on packing cost 
    public double calculatePackingcostTax(double expectedSubTotal,double packingcostTaxPercent )
    {
    	double expPackingcostTax=(expectedSubTotal*packingcostTaxPercent)/100;
    	return expPackingcostTax; 	
    }
    
    //find menu tax amount without discount
    public double calculateItemTax()
   	
       {
       	
    	Collection<MenuItem> items = testContextSetup.getCart().getCartItems();
  	    double itemtaxtotal=0;
  	    for (MenuItem item : items) 
  	    {
  	    double taxpercent=item.getTax();
  	    double expTotalItemPrice = item.getTotalPrice();
  	    itemtaxtotal+=(expTotalItemPrice * (taxpercent/100));
  	    }
       return itemtaxtotal;
      }
    //Set the Summary Tota
    public void verifyCheckoutSummary_withoutCoupniscount(double expSubTotal,double packingcost,double packingcostTax, double deliveryCharge, boolean isDeliveryOrder)
    {
    	double expPackingcost=calculatePackingcost(expSubTotal,packingcost);
    	//expDeliveryCharge= DeliveryCharge;
    	double expPackingcosttax=calculatePackingcostTax(expPackingcost,packingcostTax);
    	double expItemtaxtotal=calculateItemTax();
    	double expTotaltax=expPackingcosttax+expItemtaxtotal;
    	 // Add delivery charge only for delivery orders
        double expToPay = expSubTotal + expPackingcost + expPackingcosttax + expItemtaxtotal;
        if (isDeliveryOrder) 
        {
            expToPay += deliveryCharge;
        }
    	
    	// Store values in Cart
    	testContextSetup.getCart().setExpPackingcost(expPackingcost);
    	testContextSetup.getCart().setExpPackingcosttax(expPackingcosttax);
    	testContextSetup.getCart().setExpItemtaxtotal(expItemtaxtotal);
    	testContextSetup.getCart().setExpTotaltax(expTotaltax);
    	testContextSetup.getCart().setExpToPay(expToPay);
    	
    	
        String  actCartsubtotal=testContextSetup.getCheckOutPageActions().getCheckoutPageSubToal();
        String actPackingcost=testContextSetup.getCheckOutPageActions().getCheckoutPagePackingCost();
        String actTotaltax=testContextSetup.getCheckOutPageActions().getCheckoutPageTotalTax();
        String actToPay=testContextSetup.getCheckOutPageActions().getToPay();
    	
        testContextSetup.getAssertionUtils().assertTextEquals(actCartsubtotal,String.valueOf(FormatUtils.formatPrice(expSubTotal)));
        testContextSetup.getAssertionUtils().assertTextEquals( actPackingcost,String.valueOf(FormatUtils.formatPrice(expPackingcost)));    	
    	//Developer need fix rount of issue in packing cost take 2.115 not round off as 2.12 
        testContextSetup.getAssertionUtils().assertTextEquals(actTotaltax, String.valueOf(FormatUtils.formatPrice(expTotaltax)));
        testContextSetup.getAssertionUtils().assertTextEquals(actToPay.substring(1),String.valueOf(FormatUtils.formatPrice(expToPay)));
    	
    }
    
    //Verify delivery charge in check out page
    public void verifyCheckOutDeliveryCharge(double expDelCharge)
    {
   	
    	testContextSetup.getCart().setExpDeliverCharge(expDelCharge);
    	String actDelCharge=testContextSetup.getCheckOutPageActions().getDeiveryCharge();
        testContextSetup.getAssertionUtils().assertTextEquals(String.valueOf(FormatUtils.formatPrice(expDelCharge)),actDelCharge);
    }
    
//Order confirmation common funcitons
    
  //Verify delivery charge in check out page
    public void verifyOrderConfirmDeliveryCharge()
    {

   	  double retrievedDeliveryCharge=(testContextSetup.getCart().getExpDeliverCharge());
   	  String actDelCharge= testContextSetup.getOrderConfirmationPageActions().getDeliveryCharge();
      testContextSetup.getAssertionUtils().assertTextEquals(String.valueOf(FormatUtils.formatPrice(retrievedDeliveryCharge)),actDelCharge);
    }
    
    //Verify order summary after complete the payment 
    public void verifyOrderconfrimPageItems(String expItemName, String expServingSize, double expTotalItemPrice, int expTotalQty,int rowIndex ) throws InterruptedException 
    {
   
    	String expOrderDate = FormatUtils.getcurrentDateTime(); // Gets current date and time in dd/MM/yyyy hh:mm a format
    	testContextSetup.getCart().setExpOrderDate(expOrderDate);
    	// Retrieve the order date from the cart
    	String retrievedOrderDate = testContextSetup.getCart().getExpOrderDate();
    	System.out.println(retrievedOrderDate);
    	String actualOrderNo=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationOrderno();
    	testContextSetup.getCart().setExpOrderNo(actualOrderNo);    	
    	String actualItemName=   testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationRowItemName(rowIndex);
    	String actuaPrice=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationRowPricet(rowIndex);
    	String actualservingSize=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationServingSize(rowIndex);
    	String actualServingTotal=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationRowServingTotalPrice(rowIndex);
    	double expItemTotal = expTotalItemPrice;
        String formattedServingSize = expTotalQty + " X " + expServingSize;
        System.out.println("Verifying item detais in order confirmation page items...");    
        expItemName=expItemName + " X " + expTotalQty;
        testContextSetup.getAssertionUtils().assertTextEquals( actualItemName,expItemName);
        // Validate Total Price
        System.err.println(expItemName);
        double actualPriceValue = Double.parseDouble(actuaPrice.substring(1)); 
        testContextSetup.getAssertionUtils().assertTextEquals(FormatUtils.formatPrice(actualPriceValue),FormatUtils.formatPrice(expItemTotal));
             // Validate Serving Size
        testContextSetup.getAssertionUtils().assertTextEquals(actualservingSize,formattedServingSize);
            // Validate Serving Size Total Price
        double servingTotal = Double.parseDouble(actualServingTotal);
        testContextSetup.getAssertionUtils().assertTextEquals(FormatUtils.formatPrice(servingTotal),FormatUtils.formatPrice(expItemTotal));
    
    }
    
    //order payment sucuss verify final summary
    public void verifyOrderconfrimPageSummary()
    {
  
    	
    	double retrievedSubTotal=testContextSetup.getCart().getCartTotal();
    	double retrievedPackingcost=testContextSetup.getCart().getExpPackingcost();
    	double retrievedPackingcosttax = testContextSetup.getCart().getExpPackingcosttax();
    	double retrievedItemtaxtotal = testContextSetup.getCart().getExpItemtaxtotal();
    	double retrievedTotaltax = testContextSetup.getCart().getExpTotaltax();
    	double retrievedToPay =testContextSetup.getCart().getExpToPay();
        String retrievedOrderDate=testContextSetup.getCart().getExpOrderDate();
        
        System.out.println("retrievedOrderDate in confirmaton Page"+retrievedOrderDate);
        String  actCartsubtotal=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationSubToal();
        String actPackingcost=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationPackingCost();
        String actTotaltax=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationTotalTax();
        String actPaid=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationPaid();
        String actualOrderAmount=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationOrderAmount();
        String actualOrderDate=testContextSetup.getOrderConfirmationPageActions().getOrderConfirmationOrderDate();
        
    	
        testContextSetup.getAssertionUtils().assertTextEquals(actCartsubtotal, String.valueOf(FormatUtils.formatPrice(retrievedSubTotal)));
        testContextSetup.getAssertionUtils().assertTextEquals(actPackingcost, String.valueOf(FormatUtils.formatPrice(retrievedPackingcost)));    	
        // Developer needs to fix rounding issue in packing cost (should be 2.115 instead of rounding to 2.12)
        testContextSetup.getAssertionUtils().assertTextEquals(actTotaltax, String.valueOf(FormatUtils.formatPrice(retrievedTotaltax)));
        testContextSetup.getAssertionUtils().assertTextEquals(actPaid, String.valueOf(FormatUtils.formatPrice(retrievedToPay)));
  //      testContextSetup.getAssertionUtils().assertTextEquals(actualOrderDate, retrievedOrderDate);    	
        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualOrderDate, retrievedOrderDate, "Time MissMatch");
        									

    }
    
    //Verify Delivery charge and Delivery address in order cofirmation page
    public void verifyConfirmation_Deliveryaddress()
    {
   	 
    	String retrievedAddress= testContextSetup.getCart().getExpDeliveryAddress();
     	 // Find all <p> elements that contain the address lines
        List<WebElement> addressLines = testContextSetup.getOrderConfirmationPageActions(). addressLinesOrderConfirm();

        // Extract the text from each <p> element and concatenate into a single string
        StringBuilder actualAddressBuilder = new StringBuilder();
        for (WebElement line : addressLines)
        {
            actualAddressBuilder.append(line.getText().trim()); // Trim to remove any unwanted spaces
            actualAddressBuilder.append(", ");  // Add a comma and space between address lines
        }

        // Remove the last comma and space, then format the result
        String actualAddress = actualAddressBuilder.toString().replaceAll(", $", "");

        // Replace the last comma with a newline character for proper formatting (pincode part)
        actualAddress = actualAddress.replace(", Pincode", "\nPincode");

        // Perform the assertion to compare expected vs. actual address
        testContextSetup.getAssertionUtils().assertTextEquals( retrievedAddress, actualAddress); 
    }
    
    public void test()
    {
    	double retrievedPackingcosttax = testContextSetup.getCart().getExpPackingcosttax();
    	double retrievedItemtaxtotal = testContextSetup.getCart().getExpItemtaxtotal();
    	double retrievedTotaltax = testContextSetup.getCart().getExpTotaltax();
    	double retrievedToPay =testContextSetup.getCart().getExpToPay();

    	// Use these values in assertions
    	System.out.println("Packing Cost Tax: " + retrievedPackingcosttax);
    	System.out.println("Item Tax Total: " + retrievedItemtaxtotal);
    	System.out.println("Total Tax: " + retrievedTotaltax);
    	System.out.println("Total Amount to Pay: " + retrievedToPay);
    }
    /**
     * Verifies the cart information after adding items.
     * @param expectedItemCount Expected item count in the cart
     * @param expectedSubtotal Expected subtotal amount
     */ 
    public void verifyCartInformation(int expectedItemCount, double expectedSubtotal) 
    {
    	 testContextSetup.getAssertionUtils().assertTextEquals(String.valueOf( testContextSetup.getCart().getCartItemCount()), String.valueOf(expectedItemCount));
    	 testContextSetup.getAssertionUtils().assertTextEquals( String.valueOf(testContextSetup.getCart().getCartTotal()), String.valueOf(expectedSubtotal));
    }
    

    
    
    
    }

  
