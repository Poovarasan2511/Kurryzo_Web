package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerLoginPage {

	 private WebDriver driver;



    @FindBy(xpath = "//a[@class='btn btn-outline-primary rounded-0']")
    public WebElement btn_Loginlink;

    @FindBy(id = "UserName")
    public WebElement txt_UserName;

    @FindBy(id = "Password")
    public WebElement txt_Password;

    @FindBy(id = "Email")
    public WebElement txt_CheckoutUserName;

    @FindBy(id = "btnViewLogin")
    public WebElement btn_CheckoutLogin;

    @FindBy(id = "txtotp")
    public WebElement btn_verifyOTP;

    @FindBy(id = "ApprovalCode")
    public WebElement txt_OTP;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    public WebElement btn_Login;

    @FindBy(xpath = "//div[contains(@class,'ps-2 fw-400')]")
    public WebElement lbl_ProfileName;

    @FindBy(xpath = "//a[normalize-space()='Profile']")
    public WebElement ddl_Profile;

    public CustomerLoginPage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Optional: Getter method for WebDriver (if required elsewhere in the framework)
   /* public WebDriver getDriver() 
    {
        return this.driver;
    }*/
}
