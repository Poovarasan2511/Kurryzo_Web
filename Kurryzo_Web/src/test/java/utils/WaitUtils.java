package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    // Use ThreadLocal<WebDriver> to ensure thread-safety in multi-threaded test execution
;

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WaitUtils
    public WaitUtils(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // Wait for a WebElement to be visible
        public void waitForElementVisible(WebElement element, int timeoutInSeconds)
        {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    
    // Wait for an element to be clickable by its WebElement
    public void waitForElementClickableElement(WebElement element, int timeoutInSeconds) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
 
 
    // Wait for an element to disappear by its locator
    public void waitForElementToDisappear(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Wait for an element to be present by its locator
    public void waitForElementPresent(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Reusable function to wait until a WebElement is visible and return it
    public WebElement waitForVisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)); // Return the visible WebElement
        } catch (Exception e) {
            return null; // Return null if element is not visible within the timeout
        }
    }

    // Return a boolean indicating whether the WebElement is visible
    public boolean waitForVisibilityBoolean(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true; // If the element is visible
        } catch (Exception e) {
            return false; // If the element is not visible
        }
    }
    
    // Wait for an element to be visible by its locator
    public void waitForElementVisibleBy(By locator, int timeoutInSeconds) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
   
    // Wait for an element to be clickable by its locator
    public void waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitForTextToBePresent(WebElement locator, String status, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator,status));
    }

  
}
