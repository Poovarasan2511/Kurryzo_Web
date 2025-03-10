package actions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cartUtiils.CommonReusableMethods;
import pageObjects.CheckoutPage;
import utils.BrowserActions;
import utils.TestContextSetup;
import utils.WaitUtils;

public class CheckOutPageActions
{
    private  CheckoutPage checkOutPage;
    private WebDriver driver;
    private WaitUtils waitUtils;
    private BrowserActions browserActions;
    TestContextSetup testContextSetup;
    // Constructor
    public CheckOutPageActions( CheckoutPage checkOutPage,WebDriver driver)
    {
        this.driver = driver;
        this.checkOutPage = new CheckoutPage(driver);  // Initialize MenuPage inside MenuPageActions
        this.waitUtils = new WaitUtils(driver);
        this.browserActions = new BrowserActions(driver);
    }
    
    //Find all delviery address
    
    public int geTtotalNoDeliveryAddress()
    {
    	int addressCount=checkOutPage.lbl_DelAllAddresss.size();
    	return addressCount;
    }
    
    public String getAllDelevieryAddress(int rowIndex)
    {
    	 return checkOutPage.getAllDelveryAddress(rowIndex).getText().trim();
    }
    
    public WebElement selectDeiveryAddress(int rowIndex)
    {
    	 return checkOutPage.selectDelveryAddressIndex(rowIndex);
    }
    
    public String getDeiveryCharge()
    
    {
  
    			return checkOutPage.lbl_DelChargeAmt.getText().trim().substring(1);   	
    }

    // Get Item row item name
    public String getCheckoutRowItemName(int rowIndex) 
    {
        return checkOutPage.getCheckoutRowItemNameByIndex(rowIndex).getText();
    }
    
    // Get Item Price
    public String getCheckoutRowPrice(int rowIndex) 
    {
        return checkOutPage.getCheckoutRowPriceElementByIndex(rowIndex).getText();
    }

    //  Get Serving Size
    public String getCheckoutRowServingSize(int rowIndex)
    {
        return checkOutPage.getCheckoutRowServingSizeElementByIndex(rowIndex).getText();
    }

    //  Get Serving Total Price
    public String getCheckoutRowServingTotalPrice(int rowIndex) 
    {
        return checkOutPage.getCheckoutRowServingTotalPriceElementByIndex(rowIndex).getText();
    }

    //  Get Check page summary
   
    public String getCheckoutPageSubToal() 
    {
        return checkOutPage.lbl_subTotalAmt.getText().trim().substring(1);
    }
    
    public String getCheckoutPagePackingCost() 
    {
        return checkOutPage.lbl_packingCostAmt.getText().trim().substring(1);
    }
    
    public String getCheckoutPageTotalTax() 
    {
        return checkOutPage.lbl_taxAmt.getText().trim().substring(1);
    }
    
    public void  getCheckoutPayElement() throws InterruptedException
    {
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
      WebElement payment=waitUtils.waitForVisibility(checkOutPage.btn_Payment, 20);
     // payment.click();
      browserActions.scrollToElementAndClick(payment, 20);
      //waitUtils. waitForElementClickableElement(payment, 20);
     // browserActions.moveToElementAndClick(payment);
      Thread.sleep(500);
    }
    
    
   
    public String getToPay() 
    {
      return checkOutPage.lbl_toPayAmt.getText();
    }
    
    
    
   

}
