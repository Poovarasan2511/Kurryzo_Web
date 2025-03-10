package actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import cartUtiils.CommonReusableMethods;
import pageObjects.MenuPage;
import pageObjects.PaymentPage;
import utils.BrowserActions;
import utils.TestContextSetup;
import utils.WaitUtils;

public class PaymentPageActions 
{
	
   private PaymentPage paymentPage ;
   private WebDriver driver;
   private WaitUtils waitUtils;
   private BrowserActions browserActions;
    TestContextSetup testContextSetup;
	    // Constructor
	    public PaymentPageActions(PaymentPage paymentPage,WebDriver driver) 
	    {
	        this.driver = driver;
	        this.paymentPage = new PaymentPage(driver);  // Initialize MenuPage inside MenuPageActions
	        this.waitUtils = new WaitUtils(driver);
	        this.browserActions = new BrowserActions(driver);
	     
	    }
	    
	    public void selectnetbanking() throws InterruptedException 
	    {
	   	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   	   paymentPage.radio_netbanking.click();
	   	   paymentPage.btn_demobanking.click();
	   	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));;
	   	   paymentPage.btn_paynow.click();
	   	   paymentPage.btn_sucess.click();
	   	   Thread.sleep(500);
	    }
}
