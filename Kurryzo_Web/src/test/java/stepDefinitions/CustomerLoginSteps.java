package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import actions.CustomerLoginActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.AssertionUtils;
import utils.Constant;
import utils.ReadJson;
import utils.TestContextSetup;

public class CustomerLoginSteps 
{

    private static final Logger logger = LogManager.getLogger(CustomerLoginSteps.class);
    private TestContextSetup testContextSetup;
   // private CustomerLoginActions customerLoginActions;



    public CustomerLoginSteps(TestContextSetup testContextSetup) throws IOException 
    {
        this.testContextSetup = testContextSetup;
       
     
    }

    @Given("^Open Browser and enter customer url$")
    public void open_Browser_and_customer_url() 
    {
    	System.err.println("Test");
        logger.info("Verifying user is on the GreenCart Landing page.");
        String customerUrl =  testContextSetup.getProperties().getProperty("customerUrl");
        testContextSetup.getDriver().get(customerUrl); 
    }

    @When("^I click on Login Button in store list page$")
    public void i_click_on_Login_Button_in_store_list_page() 
    {
    	testContextSetup.getLoginActions().loginAsCustomer();  // 

        // Validate profile visibility after login
        Boolean isProfileVisible = testContextSetup.getLoginActions().isProfileNameVisible();
      
    }
}
