package actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.CheckoutPage;
import pageObjects.OrderConfirmationPage;
import utils.BrowserActions;
import utils.TestContextSetup;
import utils.WaitUtils;

public class OrderConfirmationPageActions
{

    private  OrderConfirmationPage orderConfirmationPage;
    private WebDriver driver;
    private WaitUtils waitUtils;
    private BrowserActions browserActions;
    TestContextSetup testContextSetup;
    // Constructor
    public OrderConfirmationPageActions(OrderConfirmationPage orderConfirmationPage,WebDriver driver)
    {
        this.driver = driver;
        this.orderConfirmationPage = new OrderConfirmationPage(driver);  // Initialize MenuPage inside MenuPageActions
        this.waitUtils = new WaitUtils(driver);
        this.browserActions = new BrowserActions(driver);
    }
    

    // Get Item row item name
 // Get item row item name
    public String getOrderConfirmationRowItemName(int rowIndex) {
        return orderConfirmationPage.getOrderConfirmRowItemNameByIndex(rowIndex).getText();
    }

    
    // Get Item Price
    public String getOrderConfirmationRowPricet(int rowIndex) 
    {
        return orderConfirmationPage.getOrderConfirmRowPriceElementByIndex(rowIndex).getText().substring(1);
    }

    //  Get Serving Size
    public String getOrderConfirmationServingSize(int rowIndex)
    {
        return orderConfirmationPage.getOrderConfirmRowServingSizeElementByIndex(rowIndex).getText();
    }

    //  Get Serving Total Price
    public String getOrderConfirmationRowServingTotalPrice(int rowIndex) 
    {
        return orderConfirmationPage.getOrderConfirmRowServingTotalPriceElementByIndex(rowIndex).getText().substring(1);
    }

    //  Get Check page summary
   
    public String getOrderConfirmationSubToal() 
    {
        return orderConfirmationPage.lbl_subTotalAmt.getText().trim().substring(1).trim();
    }
    
    public String getOrderConfirmationPackingCost() 
    {
        return orderConfirmationPage.lbl_packingcostAmt.getText().trim().substring(1).trim();
    }
    
    public String getOrderConfirmationTotalTax() 
    {
        return orderConfirmationPage.lbl_taxesAmt.getText().trim().substring(1).trim();
    }
    
    
    public String getOrderConfirmationTotal() 
    {
        return orderConfirmationPage.lbl_totalAmt.getText().trim().substring(1).trim();
    }
    
    public String getOrderConfirmationPaid() 
    {
        return orderConfirmationPage.lbl_paidAmt.getText().trim().substring(1).trim();
    }
    
    public String getOrderConfirmationOrderAmount() 
    {
        return orderConfirmationPage.lbl_orderamount.getText().trim().substring(16).trim();
    }
    
    
    public String getOrderConfirmationOrderno() 
    {
        return orderConfirmationPage.lbl_orderno.getText().trim().substring(10).trim();
    }
    
    public String getOrderConfirmationOrderDate() 
    {
        return orderConfirmationPage.lbl_orderdate.getText().substring(12);
    }
    
    public String getOrderConfirmationPickupDate() 
    {
        return orderConfirmationPage.lbl_Pickupdate.getText().trim().substring(12).trim();
    }
    
    
    public String getOrderConfirmationDeliveryCharge() 
    {
        return orderConfirmationPage.lbl_Deliverydate.getText().trim().substring(2).trim();
    }
    
    public String getDeliveryCharge()
    {
    	return orderConfirmationPage.lbl_delChargeAmt.getText().trim().substring(2);
    }
    
    
    
    public List<WebElement> addressLinesOrderConfirm()
    {
 
    	 List<WebElement> addressLines = orderConfirmationPage.lbl_DeliverAddress;
    	 return addressLines;
    }
    
    
  

    
    
}
