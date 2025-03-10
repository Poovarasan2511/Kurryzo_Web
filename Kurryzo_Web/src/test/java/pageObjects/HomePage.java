package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{

    private WebDriver driver;  // Use ThreadLocal to store WebDriver instance for each thread

    // Page Object Elements
    @FindBy(id = "txtallsearch")
    public WebElement txt_search;

    @FindBy(xpath = "//a[@onclick='getLocation()']")
    public WebElement icon_Location;

    public HomePage(WebDriver driver) 
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Optional: Getter method for WebDriver (if needed for direct access in other parts of your framework)
}
