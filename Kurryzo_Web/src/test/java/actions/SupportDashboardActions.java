package actions;

import org.openqa.selenium.WebDriver;

import pageObjects.BoMyOrdersPage;
import pageObjects.StoreListPage;
import pageObjects.SupportDashboardPage;
import utils.BrowserActions;
import utils.WaitUtils;

public class SupportDashboardActions {

	private SupportDashboardPage supportDashboardPage;
	private WebDriver driver; // ✅ Use WebDriver instead of ThreadLocal<WebDriver>
	private WaitUtils waitUtils;
	private BrowserActions browserActions;
	private WebDriver incognitoDriver;

	// ✅ Constructor accepting WebDriver
	public SupportDashboardActions(SupportDashboardPage supportDashboardPage, WebDriver incognitodriver) {
		this.supportDashboardPage = new SupportDashboardPage(incognitodriver);
		this.incognitoDriver = incognitodriver;
		this.waitUtils = new WaitUtils(incognitodriver);
		this.browserActions = new BrowserActions(incognitodriver);
	}

	public void clickInvoiceOpen(String invoice) throws InterruptedException {
		waitUtils.waitForElementClickableElement(supportDashboardPage.searchInvoiceOpen, 20);
		//Thread.sleep(8000);
		supportDashboardPage.searchInvoiceOpen.sendKeys(invoice);

		supportDashboardPage.clickInvoice(invoice);
	}

	public void clickInvoicePreparation(String invoice)  {
		waitUtils.waitForElementClickableElement(supportDashboardPage.searchInvoicePreparation, 20);
		//Thread.sleep(8000);
		supportDashboardPage.searchInvoicePreparation.clear();
		supportDashboardPage.searchInvoicePreparation.sendKeys(invoice);
		supportDashboardPage.clickInvoice(invoice);
	}
	
	public void clickInvoiceInProcess(String invoice)  {
		waitUtils.waitForElementClickableElement(supportDashboardPage.searchInvoiceInProcess, 20);
		//Thread.sleep(8000);
		supportDashboardPage.searchInvoiceInProcess.clear();
		supportDashboardPage.searchInvoiceInProcess.sendKeys(invoice);
		supportDashboardPage.clickInvoice(invoice);
	}

	
	public void clickInvoiceCompleted(String invoice)  {
		waitUtils.waitForElementClickableElement(supportDashboardPage.searchInvoiceCompleted, 20);
	//	supportDashboardPage.searchInvoicePreparation.clear();
		supportDashboardPage.searchInvoiceCompleted.sendKeys(invoice);
		supportDashboardPage.clickInvoice(invoice);
	}


	
	public String getStatus() {
		String status = supportDashboardPage.lbl_status.getText();
		return status.split("MOVE TO DELIVERY")[0].trim();
	}

	public void clickBackToOrderList() {
		supportDashboardPage.btn_backToOrderList.click();
	}
	
	public void clickPreparationTab() {
		supportDashboardPage.tab_preparation.click();
	}
	
	public void clickInProcessTab() {
		supportDashboardPage.tab_inProcess.click();
	}

	public void clickCompletedTab() {
		supportDashboardPage.tab_completed.click();
	}

	
	

}
