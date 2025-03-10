package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement search;

    @FindBy(css = "h4.product-name")
    private WebElement productName;

    @FindBy(linkText = "Top Deals")
    private WebElement topDeals;

    @FindBy(css = "a.increment")
    private WebElement increment;

    @FindBy(css = ".product-action button")
    private WebElement addToCart;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initialize page elements
    }

    public void searchItem(String name) {
        search.sendKeys(name);
    }

    public String getProductName() {
        return productName.getText();
    }

    public void incrementQuantity(int quantity) {
        int i = quantity - 1;
        while (i > 0) {
            increment.click();
            i--;
        }
    }

    public void addToCart() {
        addToCart.click();
    }

    public void selectTopDealsPage() {
        topDeals.click();
    }

    public String getTitleLandingPage() {
        return driver.getTitle();
    }
}
