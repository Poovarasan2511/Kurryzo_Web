package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupportDashboardPage {

    private WebDriver driver;  

    public SupportDashboardPage(WebDriver driver) {
        this.driver = driver;  
        PageFactory.initElements(driver, this);  
    }

    @FindBy(xpath="(//input[@type='search'])[1]")
    public WebElement searchInvoiceOpen;
    
    @FindBy(xpath="(//input[@type='search'])[3]")
    public WebElement searchInvoicePreparation;
    
    @FindBy(xpath="(//input[@type='search'])[2]")
    public WebElement searchInvoiceInProcess;

    @FindBy(xpath="(//input[@type='search'])[4]")
    public WebElement searchInvoiceCompleted;

    
    @FindBy(xpath="(//td[@style=' color: darkgreen;'])[1]")
    public WebElement lbl_status;
    
    @FindBy(xpath="//a[@class='btn_1']")
    public WebElement btn_backToOrderList;

    @FindBy(xpath="//button[@id='3']")
    public WebElement tab_preparation;

    @FindBy(xpath="//button[@id='2']")
    public WebElement tab_inProcess;

    
    @FindBy(xpath="//button[@id='4']")
    public WebElement tab_completed;

    
  
  
    public void clickInvoice(String invoiceNo) {
    	driver.findElement(By.xpath("//*[contains(text(),'"+invoiceNo+"')]")).click();
    }
    
  
    
}
