package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CheckoutPage 
{
	private WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	  @FindBy(xpath="//div[@class='mt-3 d-flex p-2 justify-content-between align-items-center']/div/div/p/b")
	  public WebElement lbl_storeName;
	  
	  @FindBy(xpath="(//div[contains(@class,'small text-muted')])[2]")
	  public WebElement lbl_storeaddress;
	    
	  @FindBy(xpath="//span[contains(.,'Add More Item')]")
	  public WebElement lnk_addMoreItem;
	 
	  @FindBy(xpath="//span[contains(text(),'View & Apply Coupon')]")
	  public WebElement btn_viewCoupon;
	  
	  @FindBy(id="CouponNumber")
	  public WebElement txt_couponNo;
	  
	  @FindBy(id="apply")
	  public WebElement btn_applyCoupon;
	  
	  @FindBy(xpath="//button[contains(text(),'YAY!')]")
	  public WebElement btn_applyCouponPopup;
	  
	  @FindBy(xpath="//b[contains(.,'Sub Total')]")
	  public WebElement lbl_subTotal;
	  
	  @FindBy(xpath="//b[contains(.,'Sub Total')]/following::div[1]")
	  public WebElement lbl_subTotalAmt;
	  
	  @FindBy(xpath="//b[contains(text(),'Discount')]")
	  public WebElement lbl_Discount;
	  
	  @FindBy(xpath="//b[contains(text(),'Discount')]/following::div[1]")
	  public WebElement lbl_DiscountAmt;
	  
	  @FindBy(xpath="//b[contains(text(),'Packing Cost')]")
	  public WebElement lbl_packingCost;
	  
	  @FindBy(xpath="//b[contains(text(),'Packing Cost')]/following::div[1]")
	  public WebElement lbl_packingCostAmt;
	  
	  @FindBy(xpath="//b[normalize-space()='Taxes']")
	  public WebElement lbl_taxes;
	  
	  @FindBy(xpath="//b[normalize-space()='Taxes']/following::div[1]")
	  public WebElement lbl_taxAmt;
	  
	  @FindBy(xpath="//b[contains(.,'Tip Amount')]")
	  public WebElement lbl_tip;
	  
	  @FindBy(xpath="//b[contains(.,'Tip Amount')]/following::div[1]")
	  public WebElement lbl_tipAmt;
	  
	  @FindBy(xpath="//b[contains(.,'To Pay')]")
	  public WebElement lbl_toPay;
	  
	  @FindBy(xpath="//b[contains(.,'To Pay')]/following::div[1]")
	  public WebElement lbl_toPayAmt;
	  
	  @FindBy(xpath="//div[@id='Reload1']/p[1]/b[1]")
	  public WebElement lbl_toPayAmtPartyOrder;

	  @FindBy(xpath="//textarea[@id='txtnotes']")
	  public WebElement lbl_Notes;
	  
	  @FindBy(xpath = "//div[@class='d-flex']//button[@id='btnWLPayment']")
	  //By(xpath = "(//button[contains(@type,'submit')])[1]")
	  public WebElement btn_Payment;
	  
	  @FindAll(@FindBy(xpath = "//div[@id='Addressholder']//div//div[1]//div[1]//div[1]//p[1]"))
	   public  List<WebElement> lbl_DelAllAddresss;
	  
	  @FindBy(xpath = "//div[@id='Addressholder']//div[2]//div[1]//div[1]//div[1]//p[1]")
	  public WebElement lbl_DelAddresss;
	  
	  @FindBy(xpath = "//div[@id='Addressholder']//div[2]//div[1]//div[1]//div[1]//p[1]//following::div[4]/button[2]")
	  public WebElement btn_DelAddresss;
	
	  @FindBy(xpath = "//b[contains(text(),'Delivery Charge')]")
	  public WebElement lbl_DelCharge;
	  
	  @FindBy(xpath = "//b[contains(text(),'Delivery Charge')]/following::div[1]")
	  public WebElement lbl_DelChargeAmt;
	  
	//Dynamic chceck out item summary  row eleements
	  
	  // Get Cart Item Name by Row Index
	    public WebElement getAllDelveryAddress(int rowIndex) 
	    {
	        return driver.findElement(By.xpath("//div[@id='Addressholder']//div["+ rowIndex +"]//div[1]//div[1]//div[1]//p[1]"));
	    }
	    
	    public WebElement selectDelveryAddressIndex(int rowIndex)
	    {	    	
	    	return driver.findElement(By.xpath("//div[@id='Addressholder']//div["+ rowIndex +"]//div[1]//div[1]//div[1]//p[1]//following::div[4]/button[2]\n"+ ""));
	    }
	    
	    // Get Cart Item Name by Row Index
	    public WebElement getCheckoutRowItemNameByIndex(int rowIndex) 
	    {
	        return driver.findElement(By.xpath("//div[@class='d-flex p-2 justify-content-between']["+rowIndex+"]/div[1]"));
	    }

	    // Get Price Element by Row Index
	    public WebElement getCheckoutRowPriceElementByIndex(int rowIndex)
	    {
	        return driver.findElement(By.xpath("//div[@class='d-flex p-2 justify-content-between']["+rowIndex+"]/div[1]/following::div[6]"));
	    }

	    // Get Serving Size Element by Row Index
	    public WebElement getCheckoutRowServingSizeElementByIndex(int rowIndex)
	    {
	        return driver.findElement(By.xpath("//div[@class='d-flex p-2 justify-content-between']["+rowIndex+"]/div[1]/following::div[9]/p[1]/b[1]"));
	    }

	    //  Get Total Serving Price by Row Index
	    public WebElement getCheckoutRowServingTotalPriceElementByIndex(int rowIndex) 
	    {
	        return driver.findElement(By.xpath("//div[@class='d-flex p-2 justify-content-between']["+rowIndex+"]/div[1]/following::div[10]/p/b/span[1]"));
	    }


	  

	
	
}
