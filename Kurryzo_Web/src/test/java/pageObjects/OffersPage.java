package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class OffersPage {

    private WebDriver driver;

    // PageFactory initialization
    @FindBy(xpath = "//input[@type='search']")
    private WebElement search;

    @FindBy(css = "tr td:nth-child(1)")
    private WebElement productName;

    public OffersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchItem(String name) {
        search.sendKeys(name);
    }

    public void getSearchText() {
        search.getText();
    }

    public String getProductName() {
        return productName.getText();
    }
}
