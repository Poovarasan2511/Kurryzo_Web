package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoMyOrdersPage {

	private WebDriver driver;

	public BoMyOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "select2-storeSelect-container")
	public WebElement ddl_selectStore;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement txt_searchStore;

	@FindBy(id = "btnLogin")
	public WebElement btn_login;

	@FindBy(xpath = "(//input[@type='search'])[1]")
	public WebElement search_invoiceNew;

	@FindBy(xpath = "(//input[@type='search'])[3]")
	public WebElement search_invoicePreparation;

	@FindBy(xpath = "(//input[@type='search'])[2]")
	public WebElement search_invoiceInProcess;

	@FindBy(xpath = "(//input[@type='search'])[4]")
	public WebElement search_invoicePickupDelivery;

	@FindBy(xpath = "//button[@value='4']")
	public WebElement btn_confirmOrderNow;

	@FindBy(xpath = "//button[@value='3']")
	public WebElement btn_confirmOrderAhead;

	@FindBy(xpath = "//button[@value='5']")
	public WebElement btn_readyForPickup;

	@FindBy(xpath = "//a[@id='btnstoresave']")
	public WebElement btn_backToOrderList;

	@FindBy(xpath = "//button[@value='6']")
	public WebElement btn_delivered;

	@FindBy(xpath = "//button[@value='4']")
	public WebElement btn_preparation;

	@FindBy(xpath = "(//button[@type='button'])[5]")
	public WebElement pop_yes;
	
	@FindBy(xpath = "(//button[@type='button'])[6]")
	public WebElement pop_yesPartyOrder;


	@FindBy(xpath = "//div[@id='3']")
	public WebElement tab_preparation;

	@FindBy(xpath = "(//td[@style=' color: darkgreen;'])[1]")
	public WebElement lbl_status;

	// public final By ELEMENT = By.xpath("//a[@id='btnstoresave']");

}
