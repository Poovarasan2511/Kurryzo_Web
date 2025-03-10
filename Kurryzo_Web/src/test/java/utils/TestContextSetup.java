package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import actions.BoMyOrdersActions;
import actions.CheckOutPageActions;
import actions.CustomerLoginActions;
import actions.HomePageActions;
import actions.MenuPageActions;
import actions.OrderConfirmationPageActions;
import actions.PaymentPageActions;
import actions.StoreListPageActions;
import actions.SupportDashboardActions;
import cartUtiils.Cart;
import pageObjects.BoLoginPage;
import pageObjects.BoMyOrdersPage;
import pageObjects.CheckoutPage;
import pageObjects.CustomerLoginPage;
import pageObjects.HomePage;
import pageObjects.MenuPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import pageObjects.StoreListPage;
import pageObjects.SupportDashboardPage;

public class TestContextSetup {

	private TestBase testBase;
	private CustomerLoginActions loginActions;
	private HomePageActions homePageActions;
	private StoreListPageActions storeListPageActions;
	private MenuPageActions menuPageActions;
	private CheckOutPageActions checkOutPageActions;
	private PaymentPageActions paymentPageActions;
	private OrderConfirmationPageActions orderConfirmationPageActions;
	private AssertionUtils assertionUtils;
	private WaitUtils waitUtils;
	private BrowserActions browserActions;
	private JSONObject objDailyMenu;
	// ThreadLocal for Cart to ensure thread safety during parallel execution
	// private ThreadLocal<Cart> cart;
	// ThreadLocal for WebDriver to ensure thread safety during parallel execution
	private ThreadLocal<Cart> cart = new ThreadLocal<>();

	private BoLoginPage boLoginPage;
	private JSONObject objPartnerLogin;
	private BoLoginPage boLoginPageIncognito;
	private BoMyOrdersActions boMyOrdersActions;
	private SupportDashboardActions supportDashboardActions;


	// Properties file path
	private Properties properties;
	WebDriver incognitoDriver;
	public TestContextSetup() throws IOException {
		// public TestContextSetup(String browser){
		// Load properties file
		properties = new Properties();
		loadProperties("src/test/resources/global.properties", properties); // Global properties file
		// anotherProperties = new Properties();
		// loadProperties("src/test/resources/another.properties", anotherProperties);
		testBase = new TestBase();
		String browserName = properties.getProperty("browser", "chrome");
		WebDriver driver = testBase.getDriver(browserName); // Initialize WebDriver with the browser name
	
	//	WebDriver incognitoDriver = testBase.getIncognitoDriver("edge");
		// other class
		assertionUtils = new AssertionUtils();
		waitUtils = new WaitUtils(driver);
		browserActions = new BrowserActions(driver);
		// cart = ThreadLocal.withInitial(Cart::new);
		// Initialize ThreadLocal for Cart
		cart.set(new Cart());

		// Initialize the Action classes
		loginActions = new CustomerLoginActions(new CustomerLoginPage(driver), driver);
		homePageActions = new HomePageActions(new HomePage(driver), driver);
		storeListPageActions = new StoreListPageActions(new StoreListPage(driver), driver);
		menuPageActions = new MenuPageActions(new MenuPage(driver), driver);
		checkOutPageActions = new CheckOutPageActions(new CheckoutPage(driver), driver);
		paymentPageActions = new PaymentPageActions(new PaymentPage(driver), driver);
		orderConfirmationPageActions = new OrderConfirmationPageActions(new OrderConfirmationPage(driver), driver);
		objDailyMenu = ReadJson.setJson(Constant.Path_jsonTestData, Constant.File_Testdata_DailyMenu);
		boLoginPage = new BoLoginPage(driver);
		//boLoginPageIncognito = new BoLoginPage(incognitoDriver);

		objPartnerLogin = ReadJson.setJson(Constant.Path_jsonTestData, Constant.File_Testdata_PartnerLogin);
		boMyOrdersActions = new BoMyOrdersActions(new BoMyOrdersPage(driver), driver);
		supportDashboardActions = new SupportDashboardActions(new SupportDashboardPage(incognitoDriver), incognitoDriver);
	}

    public void initializeIncognitoDriver() {
        if (incognitoDriver == null) { // Lazy initialization
            incognitoDriver = testBase.getIncognitoDriver("edge");
            boLoginPageIncognito = new BoLoginPage(incognitoDriver);
        }
    }

	// Utility method to load a properties file
	private void loadProperties(String filePath, Properties properties) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			properties.load(fileInputStream);
		}
	}

	// Getter for the first properties file
	public Properties getProperties() {
		return properties;
	}

//	public WebDriver getDriver() {
//		return testBase.getDriver(""); // Returning WebDriver instance
//	}
//	
	public WebDriver getDriver() {
	    try {
	        return testBase.getDriver("");
	    } catch (IllegalArgumentException e) {
	        // Log the exception (recommended)
	        System.err.println("Invalid argument while getting WebDriver: " + e.getMessage());
	    }
	    return null; // Return a default value or handle it appropriately
	}

	
//	public WebDriver getIncognitoDriver() {
//		return testBase.getIncognitoDriver(""); // Returning WebDriver instance
//	}

	public WebDriver getIncognitoDriver() {
	    try {
	        return testBase.getIncognitoDriver("");
	    } catch (IllegalArgumentException e) {
	        // Log the exception (recommended)
	        System.err.println("Invalid argument while getting WebDriver: " + e.getMessage());
	    }
	    return null; // Return a default value or handle it appropriately
	}

	// cart

	// Getter for Cart (Thread-safe)
	public Cart getCart() {

		return cart.get();
	}

	// Setter for Cart (if needed)
	public void setCart(Cart newCart) {
		cart.set(newCart);
	}

	// Remove Cart from ThreadLocal (cleanup after test)
	public void removeCart() {
		cart.remove();
	}

	// Getter for utils
	public AssertionUtils getAssertionUtils() {
		return assertionUtils; // ✅ Added getter for AssertionUtils
	}

	public WaitUtils getWaitUtils() {
		return waitUtils; // ✅ Added getter for AssertionUtils
	}

	public BrowserActions browserActions() {
		return browserActions; // ✅ Added getter for AssertionUtils
	}

	// Getter for actions
	public CustomerLoginActions getLoginActions() {
		return loginActions;
	}

	public HomePageActions getHomePageActions() {
		return homePageActions;
	}

	public StoreListPageActions getStoreListPageActions() {
		return storeListPageActions;
	}

	public MenuPageActions getMenuPageActions() {
		return menuPageActions;
	}

	public CheckOutPageActions getCheckOutPageActions() {
		return checkOutPageActions;
	}

	public PaymentPageActions getpaymentPageActions() {
		return paymentPageActions;
	}

	public OrderConfirmationPageActions getOrderConfirmationPageActions() {
		return orderConfirmationPageActions;
	}

	public JSONObject getObjDailyMenu() {
		return objDailyMenu; // ✅ Getter for JSON data
	}

	public void cleanup() {
		testBase.quitDriver(); // Quit driver when cleanup
	}
	
	public void cleanupIncognito() {
		testBase.quitIncognitoDriver(); // Quit driver when cleanup
	}


	public BoLoginPage getBoLoginPage() {
		return boLoginPage;
	}
	
	public BoLoginPage getBoLoginPageIncognito() {
		return boLoginPageIncognito;
	}


	public JSONObject getObjPartnerLogin() {
		return objPartnerLogin; // ✅ Getter for JSON data
	}

	public BoMyOrdersActions getBoMyOrdersActions() {
		return boMyOrdersActions;
	}
	
	public SupportDashboardActions getSupportDashboardActions() {
		return supportDashboardActions;
	}


}
