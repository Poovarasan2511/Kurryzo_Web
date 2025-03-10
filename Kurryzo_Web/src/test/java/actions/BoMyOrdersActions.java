package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import pageObjects.BoMyOrdersPage;
import utils.TestContextSetup;
import utils.WaitUtils;

public class BoMyOrdersActions {

	private WebDriver driver;
	private WaitUtils waitUtils;
	private BoMyOrdersPage boMyOrdersPage;
	TestContextSetup testContextSetup;

	public BoMyOrdersActions(BoMyOrdersPage boMyOrdersPage, WebDriver driver) {
		this.driver = driver;
		this.boMyOrdersPage = new BoMyOrdersPage(driver); // Initialize MenuPage inside MenuPageActions
		this.waitUtils = new WaitUtils(driver);
		// this.browserActions = new BrowserActions(driver);
	}

	public void SelectStore() {
		boMyOrdersPage.ddl_selectStore.click();
	}

	public void storeSelectMyOrders(String storeName) {
		boMyOrdersPage.ddl_selectStore.click();
		boMyOrdersPage.txt_searchStore.sendKeys(storeName);
		driver.findElement(By.xpath("//li[@class='select2-results__option select2-results__option--highlighted']"))
				.click();
	}

	public void searchAndSelectOrderNewTab(String orderNo) {
		boMyOrdersPage.search_invoiceNew.sendKeys(orderNo);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + orderNo + "']")).click();
	}

	public void searchAndSelectOrderPreparationTab(String orderNo) {
		boMyOrdersPage.search_invoicePreparation.sendKeys(orderNo);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + orderNo + "']")).click();
	}
	
	public void searchAndSelectOrderInProcessTab(String orderNo) {
		boMyOrdersPage.search_invoiceInProcess.sendKeys(orderNo);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + orderNo + "']")).click();
	}

	
	public void searchAndSelectOrderPickupDeliveryTab(String orderNo) {
		boMyOrdersPage.search_invoicePickupDelivery.sendKeys(orderNo);
		driver.findElement(By.xpath("//*[normalize-space(text())='" + orderNo + "']")).click();
	}


	public String getStatus() {
		return boMyOrdersPage.lbl_status.getText().trim();
	}

	public void confirmOrderNow() {
		boMyOrdersPage.btn_confirmOrderNow.click();
	}
	
	public void confirmOrderAhead() {
		boMyOrdersPage.btn_confirmOrderAhead.click();
	}


	public void clickReadyForPickup() {
		boMyOrdersPage.btn_readyForPickup.click();
	}
	
	public void clickPreparation() {
		boMyOrdersPage.btn_preparation.click();
	}

	
	public void clickDelivered() {
		boMyOrdersPage.btn_delivered.click();
	}
	
	public void clickBackToOrderList() {
//		waitUtils.waitForElementClickableElement(boMyOrdersPage.btn_backToOrderList, 20);
//		boMyOrdersPage.btn_backToOrderList.click();
		int attempts = 0;
		while (attempts < 7) {
			try {
				boMyOrdersPage.btn_backToOrderList.click();
				break;
			} catch (ElementClickInterceptedException e) {
				System.out.println(
						"Element Click Intercepted Exception detected, retrying... Attempt: " + (attempts + 1));
			} catch (StaleElementReferenceException e) {
				System.out.println("Stale element detected, retrying... Attempt: " + (attempts + 1));
			}

			attempts++;
		}
	}
	
	public void clickTabPreparation() {
		boMyOrdersPage.tab_preparation.click();
	}
	
	public void clickPopupYes() {
		boMyOrdersPage.pop_yes.click();
	}
	
	public void clickPopupYesPartyOrder() {
		boMyOrdersPage.pop_yesPartyOrder.click();
	}


}
