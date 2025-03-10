package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    private WebDriver driver;  // Use ThreadLocal for thread-safe WebDriver instance
    
    public MenuPage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="tabload_197")
	public WebElement btn_Delivery;
	
	@FindBy(id="Ordertime")
	public WebElement ddl_ordernow;
	
	@FindBy(id="Orderahead")
	public WebElement radio_Orderahead;
	
	@FindBy(id="Ordernow")
	public WebElement radio_Ordernow;
	
	@FindBy(id="DailyMenuPicker")
	public WebElement date_OrderAhead;
	
	@FindBy(xpath="//div[@class='xdsoft_time_box xdsoft_scroller_box']/div/div")
	public WebElement date_Timeslot;
	
	@FindBy(xpath="//span[normalize-space()='Restaurant Menu']")
	public WebElement lbl_menuheader;
	
	@FindBy(id="Itemsearch")
	public WebElement txt_itemsearch;
	
	@FindBy(xpath="//div[@id='Ordertime']")
	public static WebElement lbl_OrderWhen;
	
	
	@FindAll(@FindBy(xpath="(//h5[@class='fw-medium'])"))
	public List<WebElement> totalrow;
	
	@FindAll(@FindBy(xpath="//div[@id='checkStoreHours']/div[1]/div/div[1]/h5"))
	public List<WebElement> totalrow1;
	
	
    @FindBy(xpath="//div[@class='modal fade show']//div[@class='modal-header p-3']")
    // @FindBy(xpath="//div[@class='modal-dialog modal-lg']")
	//@FindBy(id="Itemsearch")
	public WebElement menupopup;
    
	@FindBy(xpath="//div[contains(@class,'fs-bigger fw-normal itemHeader')]")
	public WebElement lbl_selectOrderAhead;
	
	@FindBy(xpath="(//span[contains(.,'Submit')])[2]")
	public WebElement btn_Submit;
	
	@FindBy(xpath="(//span[contains(.,'Submit')])[2]")
	public WebElement btn_Close;
	
	@FindBy(xpath="//div[@id='pickup']//input[@type='text']")
	public WebElement lbl_DateAndTime;
	
	@FindBy(xpath="//input[@id='headcount']")
	public WebElement lbl_Headcount;
	
	@FindBy(xpath="//div[@id='swal2-html-container']")
	public WebElement popup_HeadcountWarning;

	@FindBy(xpath="//button[contains(text(),'OK')]")
	public WebElement popup_BtnHeadcountWarningOk;
	
	
	@FindBy(xpath="//span[contains(text(),'Confirm you order details')]")
	public WebElement popup_OrderDetails;
	
	@FindBy(xpath="//body/div[1]/div[6]/div[1]/div[1]/div[3]/button[2]")
	public WebElement popup_CheckoutButton;

    // Elements on the page
    @FindBy(xpath = "//h5[@class='fw-medium']")
    private List<WebElement> totalRows;

    @FindBy(xpath = "//h5[@class='fw-medium'][text()='itemName']")
    private WebElement itemByName;

    @FindBy(xpath = "(//h5[@class='fw-medium'])//following::div[1]")
    private WebElement priceElementByIndex;

    @FindBy(xpath = "(//h5[@class='fw-medium'])//following::div[@class='btn small fw-thin w-100  p-0 '][1]")
    private WebElement addToCartButton;

   // @FindBy(xpath = "//div[@class='modal']")
    //private WebElement modalPopup;
    
    @FindBy(xpath="//div[@class='modal fade show']//div[@class='modal-header p-3']")
    // @FindBy(xpath="//div[@class='modal-dialog modal-lg']")
	//@FindBy(id="Itemsearch")
    private WebElement modalPopup;

    @FindBy(xpath = "//div[contains(text(),'servingSize')]")
    private WebElement servingSizeElement;

    @FindBy(xpath = "//div[contains(text(),'servingSize')]/following::div[3]/img")
    private WebElement radioButton;

    @FindBy(xpath = "//Span[@id='Modal_TotalAmount']/following::span")
    private WebElement addButton;
    
    @FindBy(xpath = "//div[@onclick='GoToCheckout()']")
	public WebElement chekOut;
    
    @FindBy(xpath = "(//div[@class='small text-white text-start cartitemcount'])[1]")
   	public WebElement  lbl_ItemCount;
    
    @FindBy(xpath = "//div[contains(@class,'fw-400 text-white fs-18 totalAmount')][1]")
   	public WebElement lbl_TtotalAmount;
    
	@FindBy(xpath="(//div[@class='xdsoft_time_variant'])[3]")
	public List<WebElement> timeLists;
	
	@FindBy(xpath="//button[@onclick='checkoutfrompopup()']")
	public WebElement popup_checkoutPartyOrder;
	
	@FindBy(xpath="//button[@class='swal2-confirm swal2-styled']")
	public WebElement popup_partyOrderHeadCountOk;

	@FindBy(xpath="//button[@id='dropdownMenuButton1']")
	public WebElement ddl_dispatchMode;

	@FindBy(xpath="//a[@onclick='LoadMenu(197);']")
	public WebElement ddl_deliveryMode;

	
	@FindBy(xpath="(//span[@class='text-white'])[09]")
	public WebElement popup_timeSubmitButton;


    
    // Methods for dynamic elements for menu related 
    public WebElement getItemByName(String itemName) 
    {
        return driver.findElement(By.xpath("//h5[@class='fw-medium'][text()='" + itemName + "']"));
    }

    public WebElement getPriceElementByIndex(int index) 
    {
        return driver.findElement(By.xpath("(//h5[@class='fw-medium'])[" + index + "]//following::div[1]"));
       
    }

    public WebElement getAddToCartButton(int index) 
    {
        return driver.findElement(By.xpath("(//h5[@class='fw-medium'])[" + index + "]//following::div[@class='btn small fw-thin w-100  p-0 '][1]"));
    }

    public WebElement getModalPopupItemName(String itemName) 
    {
        return driver.findElement(By.xpath("//span[@class='fw-500 fs-20'][contains(.,'" + itemName + "')]"));
    }

    public WebElement getServingSizeElement(String servingSize) 
    {
        return driver.findElement(By.xpath("//div[contains(text(),'" + servingSize + "')]"));
    }

    public WebElement getServedForElement(String expServingSize)
    {
        return driver.findElement(By.xpath("//div[(text())='" + expServingSize + "']/following::div[2]"));
    }

    public WebElement getPriceElementByServingSize(String servingSize) 
    {
        return driver.findElement(By.xpath("//div[contains(text(),'" + servingSize + "')]/following::div[1]"));
    }

    public WebElement getRadioButton(String servingSize) 
    {
        return driver.findElement(By.xpath("//div[contains(text(),'" + servingSize + "')]/following::div[3]/img"));
    }

    public WebElement getAddButton() 
    {
        return driver.findElement(By.xpath("//Span[@id='Modal_TotalAmount']/following::span"));
    }
//Methods 
    
    // Get Cart Item Name by Row Index
    public WebElement getCartRowItemNameByIndex(int rowIndex) 
    {
        return driver.findElement(By.xpath("//div[@id='targetElement']//div[@class='carditems']//div[" + rowIndex + "]//div[1]//div[1]//span[1]"));
    }

    // Get Price Element by Row Index
    public WebElement getCartRowPriceElementByIndex(int rowIndex) throws InterruptedException
    
    
    {
    	Thread.sleep(200);
    	WebElement itemtotalprice=driver.findElement(By.xpath("//div[@id='targetElement']//div[@class='carditems']//div[" + rowIndex + "]//div[1]//div[1]//span[1]/following::div[1]"));
    	return itemtotalprice;
      //  return driver.findElement(By.xpath("//div[@id='targetElement']//div[@class='carditems']//div[" + rowIndex + "]//div[1]//div[1]//span[1]/following::div[1]"));
    }

    // Get Serving Size Element by Row Index
    public WebElement getCartRowServingSizeElementByIndex(int rowIndex)
    {
        return driver.findElement(By.xpath("//div[@id='targetElement']//div[@class='carditems']//div[" + rowIndex + "]//div[1]//div[1]//span[1]/following::div[4]"));
    }

    //  Get Total Serving Price by Row Index
    public WebElement getCartRowServingTotalPriceElementByIndex(int rowIndex) 
    {
        return driver.findElement(By.xpath("//div[@id='targetElement']//div[@class='carditems']//div[" + rowIndex + "]//div[1]//div[1]//span[1]/following::div[5]"));
    }

    // Get Total Quantity by Row Index
    public WebElement getCartRowTotalQtyElementByIndex(int rowIndex) 
    {
        return driver.findElement(By.xpath("//div[@id='targetElement']//div[@class='carditems']//div[" + rowIndex + "]//div[1]//div[1]//span[1]/following::div[11]"));
    }

 
}
