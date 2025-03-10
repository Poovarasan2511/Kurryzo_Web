package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage 
{
	public WebDriver driver;
	
	public PaymentPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h3[@class='accordable-heading'][contains(.,'Net Banking')]/span")
	public WebElement radio_netbanking;
	
	@FindBy(xpath="//img[contains(@alt,'Demo Bank')]")
	public WebElement btn_demobanking;
	
	@FindBy(xpath="(//label[contains(text(),'Base Price')]")
	public WebElement lbl_baseprice;
	
	@FindBy(xpath="//label[contains(text(),'Base Price')]/following :: span[2]")
	public WebElement lbl_basepriceAmt;
	
	@FindBy(xpath="(//h3[@class='price']")
	public WebElement lbl_totalprice;
	
	@FindBy(xpath="//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[5]/button[1]")
	public WebElement btn_paynow; 
	
	@FindBy(xpath="//button[contains(text(),'Success')]")
	public WebElement btn_sucess;
	
	

}
