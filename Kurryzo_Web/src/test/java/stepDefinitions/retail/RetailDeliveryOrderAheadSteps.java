package stepDefinitions.retail;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.SupportDashboardActions;
import cartUtiils.Cart;
import cartUtiils.CommonReusableMethods;
import cartUtiils.MenuItem;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SupportDashboardPage;
import utils.BrowserActions;
import utils.Constant;
import utils.GenericUtils;
import utils.TestContextSetup;

public class RetailDeliveryOrderAheadSteps {

	private TestContextSetup testContextSetup;
	private JSONObject objDailyMenu;
	private Cart cart;
	private CommonReusableMethods commonMethods;
	private WebDriver driver;
	private WebDriver incognitodriver;

	private GenericUtils genericUtils;
	private BrowserActions incognitoBrowserActions;

	private JSONObject partnerLoginDetails;
	private SupportDashboardActions incognitoSupportDashboardActions;
	private SupportDashboardPage incognitoSupportDashboardPage;

	private String boUrl;
	private String expOrderNo;// = "KZ-1899769";

	public RetailDeliveryOrderAheadSteps(TestContextSetup testContextSetup) throws IOException {
		this.driver = testContextSetup.getDriver();
		this.testContextSetup = testContextSetup;
		this.objDailyMenu = testContextSetup.getObjDailyMenu(); // ✅ Get JSON from TestContextSetup
//		this.cart = testContextSetup.getCart(); // Get Cart object from TestContextSetup
		commonMethods = new CommonReusableMethods(testContextSetup);
		genericUtils = new GenericUtils(driver);
		testContextSetup.initializeIncognitoDriver();
		this.incognitodriver = testContextSetup.getIncognitoDriver();
		this.partnerLoginDetails = testContextSetup.getObjPartnerLogin();
		incognitoSupportDashboardPage = new SupportDashboardPage(incognitodriver);
		incognitoSupportDashboardActions = new SupportDashboardActions(incognitoSupportDashboardPage, incognitodriver);
		incognitoBrowserActions = new BrowserActions(incognitodriver);

	}

	@When("I switch to order ahead for {string} and select the time for for Retail delivery Order Ahead without coupon discount")
	public void i_switch_to_order_ahead_for_and_select_the_time_for_for_retail_delivery_order_ahead_without_coupon_discount(
			String storeName) throws InterruptedException, AWTException, ParseException {
		testContextSetup.getMenuPageActions().ClickOrderAhead();
		Thread.sleep(2000);
		testContextSetup.getMenuPageActions().selectOrderAheadTime(objDailyMenu, storeName);

	}

	@Then("I make a payment for {string} for Retail Delivery ahead without discount")
	public void i_make_a_payment_for_for_retail_delivery_ahead_without_discount(String string)
			throws InterruptedException {
		testContextSetup.getCheckOutPageActions().getCheckoutPayElement();
		testContextSetup.getpaymentPageActions().selectnetbanking();
	}

	@Then("I verify the order confirmation details for the {string} for Retail Delivery ahead without discount")
	public void i_verify_the_order_confirmation_details_for_the_for_retail_delivery_ahead_without_discount(
			String string) throws InterruptedException {
		Collection<MenuItem> items = testContextSetup.getCart().getCartItems();
		int index = 1;
		// double itemtaxtotal=0;
		for (MenuItem item : items) {
			commonMethods.verifyOrderconfrimPageItems(item.getName(), item.getSize(), item.getTotalPrice(),
					(int) item.getQty(), index);
			index++;
		}

		commonMethods.verifyOrderconfrimPageSummary();

	}

	@Then("verify the adyar deivery address and deivery charge  in order confirmation page for Retail Delivery ahead without discount")
	public void verify_the_adyar_deivery_address_and_deivery_charge_in_order_confirmation_page_for_retail_delivery_ahead_without_discount() {
		commonMethods.verifyOrderConfirmDeliveryCharge();
	}

	@Then("I login to BO and switch to the store {string} then open the order and get the status for Retail Delivery ahead without discount")
	public void i_login_to_bo_and_switch_to_the_store_then_open_the_order_and_get_the_status_for_retail_delivery_ahead_without_discount(
			String storeName) throws InterruptedException {
		genericUtils.openNewTab();
		boUrl = testContextSetup.getProperties().getProperty("boUrl");
		testContextSetup.getDriver().get(boUrl);
		String storeUserName = partnerLoginDetails.getJSONObject(storeName).getString("userName");
		String storePassword = partnerLoginDetails.getJSONObject(storeName).getString("password");
		testContextSetup.getBoLoginPage().boLogin(storeUserName, storePassword);
		testContextSetup.getBoMyOrdersActions().storeSelectMyOrders(storeName);
		expOrderNo = testContextSetup.getCart().getExpOrderNo();
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderNewTab(expOrderNo);
		String statusNewBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(statusNewBo, Constant.TabNew);

	}
	
	@Then("I confirm the order for {string} for Retail Delivery ahead without discount")
	public void i_confirm_the_order_for_for_retail_delivery_ahead_without_discount(String string) {
		testContextSetup.getBoMyOrdersActions().confirmOrderAhead();
//		try {
//			testContextSetup.getBoMyOrdersActions().clickPopupYes();
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		} finally {
//			testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
//		}
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();

	    
	}


	@Then("I Open new incognito browser and Login as Supervisor and open the order for {string} for Retail Delivery ahead without discount")
	public void i_open_new_incognito_browser_and_login_as_supervisor_and_open_the_order_for_for_retail_delivery_ahead_without_discount(
			String string) throws InterruptedException {
		testContextSetup.getIncognitoDriver().get(boUrl);
		String supervisorAdminUserName = partnerLoginDetails.getJSONObject("Supervisor").getString("userName");
		String supervisorPassword = partnerLoginDetails.getJSONObject("Supervisor").getString("password");
		testContextSetup.getBoLoginPageIncognito().boLogin(supervisorAdminUserName, supervisorPassword);
		incognitoSupportDashboardActions.clickInvoiceOpen(expOrderNo);
		String statusNewSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusNewSupportDashboard, Constant.TabNew);

	}

	@Then("I open the order in InProcess tab and compare the status in support dashboard for Retail delivery Order Ahead without coupon discount")
	public void i_open_the_order_in_in_process_tab_and_compare_the_status_in_support_dashboard_for_retail_delivery_order_ahead_without_coupon_discount() throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderInProcessTab(expOrderNo);
		String statusPreparationBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(statusPreparationBo, Constant.TabInProcess);

		incognitoSupportDashboardActions.clickBackToOrderList();
		Thread.sleep(2000);
		incognitoBrowserActions.pageRefresh();
		incognitoSupportDashboardActions.clickInProcessTab();
		incognitoSupportDashboardActions.clickInvoiceInProcess(expOrderNo);

		testContextSetup.getBoMyOrdersActions().clickPreparation();
		testContextSetup.getBoMyOrdersActions().clickPopupYes();
		Thread.sleep(1500);
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
		String statusPreparationSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusPreparationSupportDashboard, Constant.TabInProcess);
		incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();


	}

	@Then("I open the order in Preparation tab and compare the status in support dashboard for Retail Delivery ahead without discount")
	public void i_open_the_order_in_preparation_tab_and_compare_the_status_in_support_dashboard_for_retail_delivery_ahead_without_discount() throws InterruptedException {
		Thread.sleep(2500);

		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderPreparationTab(expOrderNo);
		String statusPreparationBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(statusPreparationBo, Constant.TabPreparation);
		// incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();
		incognitoSupportDashboardActions.clickPreparationTab();
		incognitoSupportDashboardActions.clickInvoicePreparation(expOrderNo);
		String statusPreparationSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusPreparationSupportDashboard, Constant.TabPreparation);
		incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();


	}

	@Then("I click on Ready For Pickup and compare the status in support dashboard for Retail Delivery ahead without discount")
	public void i_click_on_ready_for_pickup_and_compare_the_status_in_support_dashboard_for_retail_delivery_ahead_without_discount() throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().clickReadyForPickup();
		// w = new WaitUtils(driver);
//			WebElement element = driver.findElement(By.xpath("(//td[@style=' color: darkgreen;'])[1]"));
		//
//			w.waitForTextToBePresent(element, Constant.TabPickupDelivery_Pickup, 20);
		Thread.sleep(2500);
//			driver.navigate().refresh();
		String readyForPickupBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(readyForPickupBo, Constant.TabPickupDelivery_Delivery);
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
		incognitoSupportDashboardActions.clickPreparationTab();
		incognitoSupportDashboardActions.clickInvoicePreparation(expOrderNo);
		String readyForPickupSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(readyForPickupSupportDashboard, Constant.TabPickupDelivery_Delivery);
		incognitoSupportDashboardActions.clickBackToOrderList();


	}

	@Then("I click on delivered and compare the status in support dashboard for Retail Delivery ahead without discount")
	public void i_click_on_delivered_and_compare_the_status_in_support_dashboard_for_retail_delivery_ahead_without_discount() throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderPickupDeliveryTab(expOrderNo);
		testContextSetup.getBoMyOrdersActions().clickDelivered();
		Thread.sleep(3000);
		String deliveredBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(deliveredBo, Constant.TabComplete);
		Thread.sleep(1500);
		incognitoBrowserActions.pageRefresh();
		Thread.sleep(500);
		incognitoSupportDashboardActions.clickCompletedTab();
		Thread.sleep(500);
		incognitoSupportDashboardActions.clickInvoiceCompleted(expOrderNo);
		String deliveredSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(deliveredSupportDashboard, Constant.TabComplete);
	}
	@After
	public void tearDown() {
		// Ensure cart is removed after test execution
		testContextSetup.removeCart();
	}

	@After // Ensure cleanup after each test step
	public void afterScenario() {
		testContextSetup.cleanup(); // Clean up ThreadLocal<Cart> after each test
		testContextSetup.cleanupIncognito();
	}


}
