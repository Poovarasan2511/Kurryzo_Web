package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoLoginPage {

	private WebDriver driver;

	public BoLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "txtemailID")
	public WebElement txt_userName;

	@FindBy(id = "txtpassword")
	public WebElement txt_password;

	@FindBy(id = "btnLogin")
	public WebElement btn_login;

	public void boLogin(String userName, String password) throws InterruptedException {
		txt_userName.sendKeys(userName);
		txt_password.sendKeys(password);
		// Thread.sleep(5000);
		btn_login.click();
	}
}
