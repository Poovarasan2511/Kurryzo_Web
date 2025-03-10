package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	public static ThreadLocal<WebDriver> incognitoDriverThreadLocal = new ThreadLocal<>();

	public WebDriver getDriver(String browserName) {
		if (driverThreadLocal.get() == null) {
			initializeDriver(browserName); // Initialize driver if not already
		}
		return driverThreadLocal.get();
	}

	public WebDriver getIncognitoDriver(String browserName) {
		if (incognitoDriverThreadLocal.get() == null) {
			initializeIncognitoDriver(browserName); // Incognito Mode
		}
		return incognitoDriverThreadLocal.get();
	}

	private void initializeDriver(String browserName) {

		switch (browserName.toLowerCase()) {
		case "chrome":

			ChromeOptions chromeOptions = new ChromeOptions();
			// WebDriverManager.chromedriver().clearResolutionCache().setup();
			System.setProperty("webdriver.chrome.verboseLogging", "true");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--remote-debugging-port=9222");
			chromeOptions.addArguments("--remote-allow-origins=*");
			String driverPath = System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe";
			// System.setProperty("webdriver.chrome.driver", "D:\\Maven\\chromedriver.exe");
			driverThreadLocal.set(new ChromeDriver(chromeOptions));

			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup(); // Fix: Using WebDriverManager
			driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			WebDriverManager.edgedriver().setup(); // Fix: Using WebDriverManager
			driverThreadLocal.set(new EdgeDriver(edgeOptions));
			break;

		case "brave":
			ChromeOptions braveOptions = new ChromeOptions();
			braveOptions.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe"); // Set
																												// Brave
																												// Browser
																												// Path
			WebDriverManager.chromedriver().setup();
			driverThreadLocal.set(new ChromeDriver(braveOptions));
			break;

		default:
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}
		driverThreadLocal.get().manage().window().maximize();
		driverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public void initializeIncognitoDriver(String browserName) {
		WebDriver driver; // Declare the WebDriver variable

		switch (browserName.toLowerCase()) {
		case "chrome":
			// Setup ChromeDriver and Incognito mode
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito"); // Incognito mode
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			// Setup FirefoxDriver and Private mode
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-private"); // Private mode for Firefox
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			// Setup EdgeDriver and InPrivate mode
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--inprivate"); // InPrivate mode for Edge
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			throw new IllegalArgumentException("Incognito mode is not supported for: " + browserName);
		}

		// Set the WebDriver instance in ThreadLocal
		incognitoDriverThreadLocal.set(driver);

		// Maximize window and set implicit wait
		incognitoDriverThreadLocal.get().manage().window().maximize();
		incognitoDriverThreadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void quitDriver() {
		WebDriver driver = driverThreadLocal.get();
		if (driver != null) {
			
			driver.quit();
			driverThreadLocal.remove();
		}
	}

	public void quitIncognitoDriver() {
		if (incognitoDriverThreadLocal.get() != null) {
			incognitoDriverThreadLocal.get().quit();
			incognitoDriverThreadLocal.remove();
		}
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}
