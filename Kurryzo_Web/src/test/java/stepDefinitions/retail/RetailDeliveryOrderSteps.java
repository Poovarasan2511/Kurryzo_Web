package stepDefinitions.retail;

import java.io.IOException;
import java.util.Collection;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import actions.SupportDashboardActions;
import cartUtiils.Cart;
import cartUtiils.CommonReusableMethods;
import cartUtiils.MenuItem;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.SupportDashboardPage;
import utils.BrowserActions;
import utils.Constant;
import utils.GenericUtils;
import utils.TestContextSetup;

public class RetailDeliveryOrderSteps {
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
	private String expOrderNo ;//= "KZ-1899769";

	public RetailDeliveryOrderSteps(TestContextSetup testContextSetup) throws IOException {
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

	
	@When("^I  change delivery as daily menu delivery mode$")
	public void i_change_delivery_as_daily_menu_delivery_mode() throws Throwable {
		testContextSetup.getMenuPageActions().selecDeliveryType();
	}

	@And("the user selects the following delivery address:")
	public void theUserSelectsDeliveryAddress(String address) throws InterruptedException {

		address = address.trim(); // Store the address globally
		commonMethods.SelectDeliveryAddress(address);

	}

	@Then("^i see the dunzo delivery charge charge for (\\d+)\\.\\d+$")
	public void i_user_the_dunzo_delivery_charge_charge_for(double expectedDeliveryCharge) throws Throwable {

		commonMethods.verifyCheckOutDeliveryCharge(expectedDeliveryCharge);
	}

	@Then("I verify the order summary without discount for daily menu delivery order from {string}")
	public void verifyOrderSummaryWithoutDiscountForDailyMenuDeliveryOrder(String storeName)
			throws InterruptedException {
		Collection<MenuItem> items = testContextSetup.getCart().getCartItems();
		int index = 1;
		// double itemtaxtotal=0;
		for (MenuItem item : items) {
			commonMethods.verifyCheckOutPageCartItems(item.getName(), item.getSize(), item.getTotalPrice(),
					(int) item.getQty(), index);
			index++;
		}

		double expDeliveryCharge = (testContextSetup.getCart().getExpDeliverCharge());
		double subtotal = testContextSetup.getCart().getCartTotal();
		double retailPackingcost = objDailyMenu.getJSONObject("storeDetails").getJSONObject(storeName)
				.getDouble("packingcost_retail");
		double retailPackingcostTax = objDailyMenu.getJSONObject("storeDetails").getJSONObject(storeName)
				.getDouble("packingcosttax_retail");
		commonMethods.verifyCheckoutSummary_withoutCoupniscount(subtotal, retailPackingcost, retailPackingcostTax,
				expDeliveryCharge, true);
	}

	@Then("I verify the order confirmation details for the {string} delivery order")
	public void verifyOrderConfirmationDetails(String restaurantName) throws InterruptedException {
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

	@Then("^verify the adyar deivery address and deivery charge  in order confirmation page$")
	public void verify_the_adyar_deivery_address_in_order_confirmation_page() throws Throwable {
		commonMethods.verifyOrderConfirmDeliveryCharge();

		// need to uncomment for address verifictaion
		// commonMethods.verifyConfirmation_Deliveryaddress();
	}

	
	@When("I login to BO and switch to the store {string} then open the order and get the status for Retail Delivery Now without discount")
	public void i_login_to_bo_and_switch_to_the_store_then_open_the_order_and_get_the_status_for_retail_delivery_now_without_discount(String storeName) throws InterruptedException {
	   
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
	
	@Then("I Open new incognito browser and Login as Supervisor and open the order for {string} for Retail Delivery Now without discount")
	public void i_open_new_incognito_browser_and_login_as_supervisor_and_open_the_order_for_for_retail_delivery_now_without_discount(String string) throws InterruptedException {
		testContextSetup.getIncognitoDriver().get(boUrl);
		String supervisorAdminUserName = partnerLoginDetails.getJSONObject("Supervisor").getString("userName");
		String supervisorPassword = partnerLoginDetails.getJSONObject("Supervisor").getString("password");
		testContextSetup.getBoLoginPageIncognito().boLogin(supervisorAdminUserName, supervisorPassword);
		incognitoSupportDashboardActions.clickInvoiceOpen(expOrderNo);
		String statusNewSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusNewSupportDashboard, Constant.TabNew);

	    
	}

	@Then("I confirm the order for {string} for Retail Delivery Now without discount")
	public void i_confirm_the_order_for_for_retail_delivery_now_without_discount(String string) {
		testContextSetup.getBoMyOrdersActions().confirmOrderNow();
//		try {
//			testContextSetup.getBoMyOrdersActions().clickPopupYes();
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		} finally {
//			testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
//		}
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();

	    
	}
	@Then("I open the order in Preparation tab and compare the status in support dashboard for Retail Delivery Now without discount")
	public void i_open_the_order_in_preparation_tab_and_compare_the_status_in_support_dashboard_for_retail_delivery_now_without_discount() {
	   
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderPreparationTab(expOrderNo);
		String statusPreparationBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(statusPreparationBo, Constant.TabPreparation);
		incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();
		incognitoSupportDashboardActions.clickPreparationTab();
		incognitoSupportDashboardActions.clickInvoicePreparation(expOrderNo);
		String statusPreparationSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusPreparationSupportDashboard, Constant.TabPreparation);
		incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();
 
	}

	@Then("I click on Ready For Pickup and compare the status in support dashboard for Retail Delivery Now without discount")
	public void i_click_on_ready_for_pickup_and_compare_the_status_in_support_dashboard_for_retail_delivery_now_without_discount() throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().clickReadyForPickup();
		// w = new WaitUtils(driver);
//		WebElement element = driver.findElement(By.xpath("(//td[@style=' color: darkgreen;'])[1]"));
//
//		w.waitForTextToBePresent(element, Constant.TabPickupDelivery_Pickup, 20);
		Thread.sleep(2000);
//		driver.navigate().refresh();
		String readyForPickupBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(readyForPickupBo, Constant.TabPickupDelivery_Delivery);
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
		incognitoSupportDashboardActions.clickPreparationTab();
		incognitoSupportDashboardActions.clickInvoicePreparation(expOrderNo);
		String readyForPickupSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(readyForPickupSupportDashboard, Constant.TabPickupDelivery_Delivery);
		incognitoSupportDashboardActions.clickBackToOrderList();

	    
	}
	@Then("I click on delivered and compare the status in support dashboard for Retail Delivery Now without discount")
	public void i_click_on_delivered_and_compare_the_status_in_support_dashboard_for_retail_delivery_now_without_discount() throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderPickupDeliveryTab(expOrderNo);
		testContextSetup.getBoMyOrdersActions().clickDelivered();
		Thread.sleep(2000);
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


/*	@Then("I login to BO and switch to the store {string} then open the order and get the status for Retail Delivery Now without discount")
	public void i_login_to_bo_and_switch_to_the_store_then_open_the_order_and_get_the_status_for_retail_delivery_now_without_discount(
			String storeName) throws InterruptedException {
		genericUtils.openNewTab();
		boUrl = testContextSetup.getProperties().getProperty("boUrl");
		testContextSetup.getDriver().get(boUrl);
		String storeUserName = partnerLoginDetails.getJSONObject(storeName).getString("userName");
		String storePassword = partnerLoginDetails.getJSONObject(storeName).getString("password");
		testContextSetup.getBoLoginPage().boLogin(storeUserName, storePassword);
		testContextSetup.getBoMyOrdersActions().storeSelectMyOrders(storeName);
		// expOrderNo = testContextSetup.getCart().getExpOrderNo();
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderNewTab(expOrderNo);
		String statusNewBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(statusNewBo, Constant.TabNew);

	}

	@Then("I Open new incognito browser and Login as Supervisor and open the order for {string} for Retail Delivery Now without discount")
	public void i_open_new_incognito_browser_and_login_as_supervisor_and_open_the_order_for_for_retail_delivery_now_without_discount(
			String string) throws InterruptedException {
		testContextSetup.getIncognitoDriver().get(boUrl);
		String supervisorAdminUserName = partnerLoginDetails.getJSONObject("Supervisor").getString("userName");
		String supervisorPassword = partnerLoginDetails.getJSONObject("Supervisor").getString("password");
		testContextSetup.getBoLoginPageIncognito().boLogin(supervisorAdminUserName, supervisorPassword);
		incognitoSupportDashboardActions.clickInvoiceOpen(expOrderNo);
		String statusNewSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusNewSupportDashboard, Constant.TabNew);

	}

	@Then("I confirm the order for {string} for Retail Delivery Now without discount")
	public void i_confirm_the_order_for_for_retail_delivery_now_without_discount(String string)
			throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().confirmOrderRetail();
//		try {
//			testContextSetup.getBoMyOrdersActions().clickPopupYes();
//		} catch (NoSuchElementException e) {
//			e.printStackTrace();
//		} finally {
//			testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
//		}
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
	}

	@Then("I open the order in Preparation tab and compare the status in support dashboard for Retail Delivery Now without discount")
	public void i_open_the_order_in_preparation_tab_and_compare_the_status_in_support_dashboard_for_retail_delivery_now_without_discount() {
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderPreparationTab(expOrderNo);
		String statusPreparationBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(statusPreparationBo, Constant.TabPreparation);
		incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();
		incognitoSupportDashboardActions.clickPreparationTab();
		incognitoSupportDashboardActions.clickInvoicePreparation(expOrderNo);
		String statusPreparationSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(statusPreparationSupportDashboard, Constant.TabPreparation);
		incognitoSupportDashboardActions.clickBackToOrderList();
		incognitoBrowserActions.pageRefresh();
	}

	@Then("I click on Ready For Pickup and compare the status in support dashboard for Retail Delivery Now without discount")
	public void i_click_on_ready_for_pickup_and_compare_the_status_in_support_dashboard_for_retail_delivery_now_without_discount()
			throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().clickReadyForPickup();
		// w = new WaitUtils(driver);
//		WebElement element = driver.findElement(By.xpath("(//td[@style=' color: darkgreen;'])[1]"));
//
//		w.waitForTextToBePresent(element, Constant.TabPickupDelivery_Pickup, 20);
		Thread.sleep(2000);
//		driver.navigate().refresh();
		String readyForPickupBo = testContextSetup.getBoMyOrdersActions().getStatus();
		Assert.assertEquals(readyForPickupBo, Constant.TabPickupDelivery_Delivery);
		testContextSetup.getBoMyOrdersActions().clickBackToOrderList();
		incognitoSupportDashboardActions.clickPreparationTab();
		incognitoSupportDashboardActions.clickInvoicePreparation(expOrderNo);
		String readyForPickupSupportDashboard = incognitoSupportDashboardActions.getStatus();
		Assert.assertEquals(readyForPickupSupportDashboard, Constant.TabPickupDelivery_Delivery);
		incognitoSupportDashboardActions.clickBackToOrderList();

	}

	@Then("I click on delivered and compare the status in support dashboard for Retail Delivery Now without discount")
	public void i_click_on_delivered_and_compare_the_status_in_support_dashboard_for_retail_delivery_now_without_discount()
			throws InterruptedException {
		testContextSetup.getBoMyOrdersActions().searchAndSelectOrderPickupDeliveryTab(expOrderNo);
		testContextSetup.getBoMyOrdersActions().clickDelivered();
		Thread.sleep(2000);
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

	} */
	

	@After
	public void tearDown() {
		// Ensure cart is removed after test execution
		testContextSetup.removeCart();

	}

	@After // Ensure cleanup after each test step
	public void zfterScenario() {
		testContextSetup.cleanup(); // Clean up ThreadLocal<Cart> after each test
		testContextSetup.cleanupIncognito();
	}
}
