package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserActions {

	private WebDriver driver; // ThreadLocal for thread safety
	private static final Logger logger = LogManager.getLogger(BrowserActions.class);
	TestContextSetup testContextSetup;

	// Constructor: Accepts ThreadLocal WebDriver
	public BrowserActions(WebDriver driver) {
		this.driver = driver;

	}

	// Method to clear input field using keyboard shortcuts
	public void clearElement(WebElement element) {
		try {
			// element.sendKeys(Keys.CONTROL + "a"); // Select all
			// element.sendKeys(Keys.DELETE); // Delete selected text
			element.sendKeys(Keys.END, Keys.SHIFT, Keys.HOME, Keys.DELETE);

			logger.info("Cleared the input field: {}", element);
		} catch (Exception e) {
			logger.error("Failed to clear the input field: {}", element, e);
			throw e;
		}
	}

	// Method to enter text into a field (clears first)
	public void enterText(WebElement element, String text) {
		try {
			// clearElement(element);

			element.sendKeys(text);
			logger.info("Entered text into field: {} | Text: {}", element, text);
		} catch (Exception e) {
			logger.error("Failed to enter text into field: {}", element, e);
			throw e;
		}
	}

	// Click an element using JavaScript (useful if normal click fails)
	public void clickElement(WebElement element) {
		try {
			// if (element.isDisplayed() && element.isEnabled()) //{
			testContextSetup.getWaitUtils().waitForElementClickableElement(element, 10);
			// WebElement webElement =
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//your-xpath")));
			element.click();
			logger.info("Clicked on the element: {}", element);
			// } else {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			logger.info("Clicked using JavaScript on element: {}", element);
			// }
		} catch (Exception e) {
			logger.error("Failed to click element: {}", element, e);
			throw e;
		}
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}

	// Press Enter key on an element
	public void enterKey(WebElement element) {
		try {
			element.sendKeys(Keys.ENTER);
			logger.info("Pressed Enter on the element: {}", element);
		} catch (Exception e) {
			logger.error("Failed to press Enter on element: {}", element, e);
			throw e;
		}
	}

	// Scroll the page by a specific x and y offset
	public void scrollPage(int x, int y) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(arguments[0], arguments[1])", x, y);
			logger.info("Scrolled page by X: {} | Y: {}", x, y);
		} catch (Exception e) {
			logger.error("Failed to scroll page", e);
			throw e;
		}
	}

	/*
	 * // Wait for an element to be visible (explicit wait) public void
	 * waitForElementVisible(WebElement element, int timeOutInSeconds) { try {
	 * WebDriverWait wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(timeOutInSeconds));
	 * wait.until(ExpectedConditions.visibilityOf(element));
	 * logger.info("Element is visible: {}", element); } catch (Exception e) {
	 * logger.error("Timeout waiting for visibility of element: {}", element, e);
	 * throw e; } }
	 */
	// Get text from an element
	public String getElementText(WebElement element) {
		try {
			String text = element.getText();
			logger.info("Retrieved text from element: {} | Text: {}", element, text);
			return text;
		} catch (Exception e) {
			logger.error("Failed to retrieve text from element: {}", element, e);
			throw e;
		}
	}

	// Get an element's attribute value
	public String getElementAttribute(WebElement element, String attribute) {
		try {
			String value = element.getAttribute(attribute);
			logger.info("Retrieved attribute '{}' from element: {} | Value: {}", attribute, element, value);
			return value;
		} catch (Exception e) {
			logger.error("Failed to retrieve attribute '{}' from element: {}", attribute, element, e);
			throw e;
		}
	}

	// Check if an element is displayed
	public boolean isElementDisplayed(WebElement element) {
		try {
			boolean isDisplayed = element.isDisplayed();
			logger.info("Element displayed status: {} | Displayed: {}", element, isDisplayed);
			return isDisplayed;
		} catch (Exception e) {
			logger.error("Failed to check if element is displayed: {}", element, e);
			return false;
		}
	}

	// Check if an element is enabled
	public boolean isElementEnabled(WebElement element) {
		try {
			boolean isEnabled = element.isEnabled();
			logger.info("Element enabled status: {} | Enabled: {}", element, isEnabled);
			return isEnabled;
		} catch (Exception e) {
			logger.error("Failed to check if element is enabled: {}", element, e);
			return false;
		}
	}

	// Perform a search and submit using the Enter key
	public void searchAndSubmit(WebElement searchBox, String searchText) {
		try {
			clearElement(searchBox);
			searchBox.sendKeys(searchText);
			searchBox.sendKeys(Keys.RETURN);
			logger.info("Performed search and submitted text: {}", searchText);
		} catch (Exception e) {
			logger.error("Failed to perform search and submit text: {}", searchText, e);
			throw e;
		}
	}

	// Common function to perform move and click action
	public void moveToElementAndClick(WebElement element) {
		try {

			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
		} catch (Exception e) {
			System.out.println("Exception in moveToElementAndClick: " + e.getMessage());
		}
	}

	// Click an element using JavaScript Executor
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollToElementAndClick(WebElement element, long timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		JavascriptExecutor js = (JavascriptExecutor) driver; // Use ThreadLocal WebDriver instance
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		// Wait until the element is clickable and then click
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// Return WebDriver from ThreadLocal
	public WebDriver getDriver() {
		return driver;
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

}
