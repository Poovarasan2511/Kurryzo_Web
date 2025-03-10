package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreListPage {

    private WebDriver driver;  // Use ThreadLocal for thread-safe WebDriver instance

    // Constructor to initialize the page object
    public StoreListPage(WebDriver driver) {
        this.driver = driver;  // Assign the ThreadLocal WebDriver instance
        PageFactory.initElements(driver, this);  // Initialize elements using WebDriver for the current thread
    }

    // WebElements for the store list page
    @FindBy(id = "txtsearch")
    public WebElement txt_search;

    @FindBy(css = "div[id='exploreconent'] div[class='mb-3']")
    public WebElement lbl_storeListHeader;

    @FindBy(xpath = "//h5[@class='card-title mb-0'][(text()='Rambhavan Test')]/preceding::div[1]")
    public WebElement lbl_StoreClosed;

    @FindBy(xpath = "//button[@id='259']")
    public WebElement tab_PartOrder;

    public void clickPartyOrderTab() {
        tab_PartOrder.click();  // Clicking on the "Party Order" tab
    }

    @FindBy(xpath = "//input[@id='Count']")
    public WebElement popup_HeadCount;

    @FindBy(xpath = "//input[@placeholder='Please Select Date and Time']")
    public WebElement txt_DateAndTime;

    @FindBy(xpath = "//div[@class='xdsoft_label xdsoft_year']")
    public WebElement popup_Year;

    @FindBy(xpath = "//div[@data-value='2026']")
    public WebElement popup_YearDropDown;

    @FindBy(xpath = "//div[@class='xdsoft_label xdsoft_month']")
    public WebElement popup_Month;

    @FindBy(xpath = "//div[@data-hour='17' and @data-minute='30']")
    public WebElement popup_Time;

    @FindBy(xpath = "//button[@class='btn bg-green fw-500 Headcount']")
    public WebElement popup_btnSubmit;
    
    @FindBy(xpath = "(//button[@class='xdsoft_next'])[2]")
    public WebElement popup_scroll;

    
    
    // Dynamic locator for store name
    public By storeNameLocator(String storeName) 
    
    {
       // return  driver.findElement(By.xpath("//h5[@class='card-title mb-0'][(text()='" + storeName + "')]"));  // Dynamic XPath for store name
    	 return By.xpath("//h5[@class='card-title mb-0'][(text()='" + storeName + "')]");  // Dynamic XPath for store name

    }
}
