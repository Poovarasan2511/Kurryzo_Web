package stepDefinitions.resuable;



import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cartUtiils.Cart;
import cartUtiils.CommonReusableMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestContextSetup;

public class CommonStepsCustomer 
{
	 /*  TestContextSetup testContextSetup;
		 private JSONObject objDailyMenu;
		 private Cart cart ;
		 CommonReusableMethods  commonMethods;
		private WebDriver driver;

    public CommonStepsCustomer(TestContextSetup testContext) {
   	 this.driver = testContextSetup.getDriver();
 	//this.testContextSetup = testContextSetup;
     this.objDailyMenu = testContextSetup.getObjDailyMenu(); // ✅ Get JSON from TestContextSetup
     this.cart = testContextSetup.getCart(); // Get Cart object from TestContextSetup
     commonMethods = new CommonReusableMethods(testContextSetup);
    }

    @When("^I search for the store location \"([^\"]*)\"$")
    public void i_search_for_the_location(String storeName) throws Throwable 
    {
    	
    	String expectedSearchLocation = objDailyMenu.getJSONObject("storeDetails").getJSONObject(storeName).getString("searchlocation");
        testContextSetup.getHomePageActions().searchLocation(expectedSearchLocation);
       
    }

 @When("^I search for a store named \"([^\"]*)\"$")
    public void i_search_for_a_store_named(String storeName) throws Throwable 
    {
	 String expectedStoreName = objDailyMenu.getJSONObject("storeDetails").getJSONObject(storeName).getString("storeName");      // Perform store search
     testContextSetup.getStoreListPageActions().searchStore(expectedStoreName);
    }
  
    @And("^I select the store \"([^\"]*)\"$")
    public void i_select_the_store(String storeName) throws Throwable 
    {
    	 // Fetch the store details dynamically
        String expectedStoreName = objDailyMenu.getJSONObject("storeDetails").getJSONObject(storeName).getString("storeName");
        testContextSetup.getStoreListPageActions().selectStore(expectedStoreName);
    }
    
    
    //Delivery realted
    
    //Delivery realted
    
	
	@When("^I  change delivery as daily menu delivery mode$")
	public void i_change_delivery_as_daily_menu_delivery_mode() throws Throwable
	{
		testContextSetup.getMenuPageActions().selecDeliveryType();
	}
	
	@And("the user selects the following delivery address:")
	public void theUserSelectsDeliveryAddress(String address) throws InterruptedException 
	{ 
		
	        address = address.trim();  // Store the address globally
	        commonMethods.SelectDeliveryAddress(address);
		
	}*/
}
