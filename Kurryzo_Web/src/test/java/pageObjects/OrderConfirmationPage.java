package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage
{
	 private WebDriver driver;
	 
	 public OrderConfirmationPage (WebDriver driver)
	  {
		  this.driver=driver;
		  PageFactory.initElements(driver, this);
	  }

	 
	  @FindBy(xpath="//h5[contains(text(),'Thank You!')]")
	  public WebElement lbl_thankyou;
	  
	  @FindBy(xpath="//b[contains(text(),'Dear Customer,')]")
	  public WebElement lbl_dearcustomer;
	  
	  @FindBy(xpath="//b[contains(text(),'Thank you for your order! Your order has been plac')]")
	  public WebElement lbl_thankyouorder;
	 
	  @FindBy(xpath="div[class='d-flex align-items-center'] p:nth-child(1)")
	  public WebElement lbl_storeaddress;
	  
	  @FindBy(xpath="div[class='d-flex align-items-center'] p:nth-child(2)")
	  public WebElement lbl_storephone;
	  
	  @FindBy(xpath="h3[class='card-title mb-3']")
	  public WebElement lbl_storeName;
	
	  @FindBy(xpath="//span[contains(text(),'Subtotal')]")
	  public WebElement lbl_subTotal;
	  
	  @FindBy(xpath="//span[contains(text(),'Subtotal')]/following-sibling :: span")
	  public WebElement lbl_subTotalAmt;
	  
	  @FindBy(xpath="//span[contains(text(),'Discount')]")
	  public WebElement lbl_Discount;
	  
	  @FindBy(xpath="//span[contains(text(),'Discount')]/following-sibling :: span")
	  public WebElement lbl_DiscountAmt;
	  
	  @FindBy(xpath="//span[contains(text(),'Packing Cost')]")
	  public WebElement lbl_packingcost;
	  
	  @FindBy(xpath="//span[contains(text(),'Packing Cost')]/following-sibling :: span")
	  public WebElement lbl_packingcostAmt;
	  
	  @FindBy(xpath="//span[contains(text(),'Delivery Fee')]")
	  public WebElement lbl_delCharge;
	  
	  @FindBy(xpath="//span[contains(text(),'Delivery Fee')]/following-sibling :: span")
	  public WebElement lbl_delChargeAmt;
	  
	  @FindBy(xpath="//span[contains(text(),'Taxes')]")
	  public WebElement lbl_taxes;
	  
	  @FindBy(xpath="//span[contains(text(),'Taxes')]/following-sibling :: span")
	  public WebElement lbl_taxesAmt;
	  
	   
	  @FindBy(xpath="//span[normalize-space()='Total']")
	  public WebElement lbl_total;
	  
	  @FindBy(xpath="//span[normalize-space()='Total']/following-sibling :: span")
	  public WebElement lbl_totalAmt;
	  
	  @FindBy(xpath="//span[contains(text(),'Paid')]")
	  public WebElement lbl_paid;
	  
	  @FindBy(xpath="//span[contains(text(),'Paid')]/following-sibling :: span")
	  public WebElement lbl_paidAmt;
	  
	  @FindBy(xpath="//h4[contains(text(),'Order Detail')]")
	  public WebElement lbl_orderdetails;
	  
	  @FindBy(xpath="//p[contains(text(),'Order Amount')]")
	  public WebElement lbl_orderamount;
	  
	  @FindBy(xpath="//p[contains(text(),'Order No')]")
	  public WebElement lbl_orderno;
	  
	  @FindBy(xpath="//p[contains(text(),'Order Date')]")
	  public WebElement lbl_orderdate;
	  
	  @FindBy(xpath="//p[contains(text(),'Pickup Date')]")
	  public WebElement lbl_Pickupdate;
	  
	  @FindBy(xpath="//p[contains(text(),'Delivery Date')]")
	  public WebElement lbl_Deliverydate;
	  
	  @FindBy(xpath="//body/div[1]/div[4]/div[1]/div[3]/div[2]/div[2]/a[1]/div[1]/p[1]")
	  public WebElement btn_ordermore;
	  
	 @FindAll(@FindBy(xpath="//div[@class='card card-body rounded-0 mb-4 bg-grey']/p[@class='mb-1']"))
      public List<WebElement> lbl_DeliverAddress;
	 
		//Dynamic chceck out item summary  row eleements
	  // Get Cart Item Name by Row Index
	    public WebElement getOrderConfirmRowItemNameByIndex(int rowIndex) 
	    {
	        return driver.findElement(By.xpath("//div[@class='carditems']/div[@class='mt-3']["+rowIndex+"]/div/div/span"));
	    }

	    // Get Price Element by Row Index
	    public WebElement getOrderConfirmRowPriceElementByIndex(int rowIndex)
	    {
	        return driver.findElement(By.xpath("//div[@class='carditems']/div[@class='mt-3']["+rowIndex+"]/div/span"));
	    }

	    // Get Serving Size Element by Row Index
	    public WebElement getOrderConfirmRowServingSizeElementByIndex(int rowIndex)
	    {
	        return driver.findElement(By.xpath("//div[@class='carditems']/div[@class='mt-3']["+rowIndex+"]/following::div[2]/div/span"));
	    }

	    //  Get Total Serving Price by Row Index
	    public WebElement getOrderConfirmRowServingTotalPriceElementByIndex(int rowIndex) 
	    {
	        return driver.findElement(By.xpath("//div[@class='carditems']/div[@class='mt-3']["+rowIndex+"]/following::div[2]/span"));
	    }


	
	  }
