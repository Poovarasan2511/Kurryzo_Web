package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.CustomerLoginPage;
import utils.WaitUtils;

import java.time.Duration;

public class CustomerLoginActions {

    private CustomerLoginPage customerLoginPage;
    private WebDriverWait wait;
    private WaitUtils waitUtils;
   
    public CustomerLoginActions(CustomerLoginPage loginPage, WebDriver driver)   
    {
        this.customerLoginPage = loginPage;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // ✅ Initialize WebDriverWait
        this.waitUtils = new WaitUtils(driver);
    }

    // Method to perform login action 
    /*
    public void loginAsCustomer(String userName, String otp) {
        wait.until(ExpectedConditions.visibilityOf(customerLoginPage.btn_Loginlink));
        customerLoginPage.btn_Loginlink.click();

        wait.until(ExpectedConditions.visibilityOf(customerLoginPage.txt_UserName));
        customerLoginPage.txt_UserName.sendKeys(userName);

        wait.until(ExpectedConditions.elementToBeClickable(customerLoginPage.btn_Login));
        customerLoginPage.btn_Login.click();

        wait.until(ExpectedConditions.visibilityOf(customerLoginPage.txt_OTP));
        customerLoginPage.txt_OTP.sendKeys(otp);

        wait.until(ExpectedConditions.elementToBeClickable(customerLoginPage.btn_verifyOTP));
        customerLoginPage.btn_verifyOTP.click();
    }*/

    public Boolean isProfileNameVisible() {
        wait.until(ExpectedConditions.visibilityOf(customerLoginPage.lbl_ProfileName));
        return customerLoginPage.lbl_ProfileName.isDisplayed();
    }
    
    public void loginAsCustomer()
    {
    	  wait.until(ExpectedConditions.visibilityOf(customerLoginPage.btn_Loginlink));
          customerLoginPage.btn_Loginlink.click();

          wait.until(ExpectedConditions.visibilityOf(customerLoginPage.txt_UserName));
          customerLoginPage.txt_UserName.sendKeys("9941533659");

          wait.until(ExpectedConditions.elementToBeClickable(customerLoginPage.btn_Login));
          customerLoginPage.btn_Login.click();

          wait.until(ExpectedConditions.visibilityOf(customerLoginPage.txt_OTP));
          customerLoginPage.txt_OTP.sendKeys("091385");


          wait.until(ExpectedConditions.elementToBeClickable(customerLoginPage.btn_verifyOTP));
          customerLoginPage.btn_verifyOTP.click();
    }

    public void openProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(customerLoginPage.ddl_Profile));
        customerLoginPage.ddl_Profile.click();
    }
}
