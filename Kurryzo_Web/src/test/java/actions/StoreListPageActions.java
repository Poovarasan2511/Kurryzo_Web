package actions;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.StoreListPage;
import utils.BrowserActions;
import utils.WaitUtils;

public class StoreListPageActions {

	private StoreListPage storelistPage;
	private WebDriver driver; // ✅ Use WebDriver instead of ThreadLocal<WebDriver>
	private WaitUtils waitUtils;
	private BrowserActions browserActions;

	// ✅ Constructor accepting WebDriver
	public StoreListPageActions(StoreListPage storelistPage, WebDriver driver) {
		this.storelistPage = storelistPage;
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		this.browserActions = new BrowserActions(driver);
	}

	// ✅ Reusable method to search for a store
	public void searchStore(String storeName) throws InterruptedException {
		waitUtils.waitForElementVisible(storelistPage.txt_search, 20);
		browserActions.clearElement(storelistPage.txt_search);
		storelistPage.txt_search.sendKeys(storeName);
		browserActions.enterKey(storelistPage.txt_search);
	}

	// ✅ Method to select a store
	public void selectStore(String storeName) throws InterruptedException, AWTException {
		System.out.println("Store name: " + storeName);

		// Safety check
		if (driver == null || storelistPage == null || waitUtils == null) {
			System.err.println("WebDriver, storelistPage, or waitUtils is null! Exiting selectStore method.");
			return;
		}

		// Wait for the store element
		// waitUtils.waitForElementVisible(storelistPage.storeNameLocator(storeName),
		// 20);

		// Find store element
		// WebElement storeElement = (storelistPage.storeNameLocator(storeName));
		// Wait for the store element
		waitUtils.waitForElementVisibleBy(storelistPage.storeNameLocator(storeName), 20);

		// Find store element
		WebElement storeElement = driver.findElement(storelistPage.storeNameLocator(storeName));

		// ✅ Check if storeElement is found before accessing its properties
		if (storeElement != null) {
			if (storeElement.isDisplayed() && storeElement.isEnabled()) {
				// ✅ Use JavaScript to click the element
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", storeElement);
				System.out.println("Store " + storeName + " clicked successfully.");
			} else {
				System.err.println("Store element is not clickable for store: " + storeName);
			}
		} else {
			System.err.println("Store element not found for store: " + storeName);
		}
	}

	public void clickPartyOrderTab() {
		storelistPage.tab_PartOrder.click(); // Clicking on the "Party Order" tab
	}

	public void setHeadcount(String headCount) {
		storelistPage.popup_HeadCount.sendKeys(headCount);
	}

	public void clickPartyOrderPopupSubmitButton() {
		storelistPage.popup_btnSubmit.click();
	}

	public void yearDropDown(String year) {
		driver.findElement(By.xpath("//div[@data-value='" + year + "']"));

	}

	public void monthDropDownClick(String month) {
		int monthInt = Integer.parseInt(month);
		int monthmodified = monthInt - 1;
		driver.findElement(By.xpath("//div[@data-value='" + monthmodified + "']")).click();
	}

	public void dateClick(String date, String month) throws InterruptedException {
		int monthInt = Integer.parseInt(month);
		int monthmodified = monthInt - 1;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			// Dynamic XPath to locate the calendar cell
Thread.sleep(2300);
//			WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(
//					By.xpath("//td[@data-date='" + date + "' and @data-month='" + monthInt + "']")));
			driver.findElement(By.xpath("//td[@data-date='" + date + "' and @data-month='" + monthmodified + "']")).click();
			//			dateElement.click(); // Click on the desired date
		} catch (StaleElementReferenceException e) {
			// Retry logic in case of stale element
			WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//td[@data-date='" + date + "' and @data-month='" + monthmodified + "']")));
			dateElement.click();
		}
	}

	public void timeDropDownClick(String hour) {
		driver.findElement(By.xpath("(//div[@class='xdsoft_time '][contains(text(),'" + hour + "')])")).click();
	}

	public void clickPopupScrollButton() {
		storelistPage.popup_scroll.click();
	}

	public void setDateAndTime(String date, String month, String year, String hour) throws InterruptedException {
		storelistPage.txt_DateAndTime.click();
		storelistPage.popup_Year.click();
		System.err.println("Year dropdown Clicked ");
		// browser.findElement(By.xpath("//div[@data-value='" + year + "']")).click();
		yearDropDown(year);
		System.err.println("Year Clicked ");
		storelistPage.popup_Month.click();
		System.err.println("Month dropdown Clicked ");
		// int monthmodified = month - 1;
		monthDropDownClick(month);
		System.err.println("Month Clicked ");
		// browser.findElement(By.xpath("//div[@data-value='" + monthmodified +
		// "']")).click();
//		try {
//			Thread.sleep(500);
//			WebElement element = browser
//					.findElement(By.xpath("//td[@data-date='" + date + "' and @data-month='" + monthmodified + "']"));
//			element.click();
//		} catch (StaleElementReferenceException e) {
//			Thread.sleep(500);
//			WebElement element = browser
//					.findElement(By.xpath("//td[@data-date='" + date + "' and @data-month='" + monthmodified + "']"));
//			element.click();
//		}

		Thread.sleep(800);
		dateClick(date, month);
		System.err.println("Date Clicked ");

		// browser.findElement(By.xpath("//td[@data-date='" + date + "' and
		// @data-month='" + monthmodified + "']")).click();
		// System.out.println("txt_DateAndTime.getText();" + txt_DateAndTime.getText());
		// System.out.println("txt_DateAndTime.attribute;" +
		// txt_DateAndTime.getAttribute("value"));
		// List<WebElement> timePickerAll =
		// browser.findElements(By.className("xdsoft_time_variant"));
		Thread.sleep(1000);
		List<WebElement> timePickerAll = driver.findElements(By.xpath("(//div[@style='margin-top: 0px;'])[3]"));
		boolean timeOptions = false;

		/*
		 * int counts = 1; String times; String timemins; WebElement element; // Loop
		 * until counts reach 24 (for all time slots from 1 to 24) for (int i = 1;
		 * counts <= 34; i++) { try { // Dynamically increase the index in the XPath for
		 * each iteration element =
		 * browser.findElement(By.xpath("(//div[@class='xdsoft_time '])[" + i + "]"));
		 * 
		 * // Fetch the 'data-hour' attribute times = element.getAttribute("data-hour");
		 * timemins = element.getAttribute("data-minute");
		 * 
		 * // Print the extracted time System.out.println("times : " + times);
		 * System.out.println("timemins : " + timemins);
		 * 
		 * 
		 * // Increase the count counts++;
		 * 
		 * } catch (Exception e) {
		 * System.out.println("No more elements found at index: " + i); break; // Break
		 * the loop if no more elements are found }
		 */

		// 06:00 AM

		while (!timeOptions) {
			for (WebElement timePicker : timePickerAll) {
				String listOfTime = timePicker.getText();
				// System.out.println("Getting Time Picker Options");

				if (listOfTime.contains(hour)) {
					Thread.sleep(200);
					timeDropDownClick(hour);
//					WebElement elementTime = browser
//							.findElement(By.xpath("(//div[@class='xdsoft_time '][contains(text(),'" + hour + "')])"));
					System.out.println("Trying to click Time Picker Options");
					// elementTime.click();

					// timePicker.click();
					// String timpicker = timePicker.getAttribute("value");
					// System.out.println("timpicker" + timpicker);

					timeOptions = true;
//				String	afterselectingdate = objStoreListPage.txt_DateAndTime.getAttribute("value");
//					afterselectingHeadCount = objStoreListPage.popup_HeadCount.getAttribute("value");

					// System.out.println("listOfTimelistOfTime " + listOfTime);
					// System.out.println("afterselectingdate" + afterselectingdate);
					break;
				}
				if (!timeOptions) {
					Thread.sleep(300);
					// WebElement scrollDownButton =
					// browser.findElement(By.xpath("(//button[@class='xdsoft_next'])[2]"));
					clickPopupScrollButton();
					// System.out.println("Trying to scroll down Time Picker Options");
					// System.out.println("Trying to scroll down Time Picker Options");
					// scrollDownButton.click();
					// Thread.sleep(800);
				}

			}
		}
	}

}
