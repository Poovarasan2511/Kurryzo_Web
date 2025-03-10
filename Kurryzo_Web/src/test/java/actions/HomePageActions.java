package actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import pageObjects.HomePage;
import utils.WaitUtils;

public class HomePageActions {

    private HomePage homePage;
    private WebDriverWait wait;
    private WaitUtils waitUtils;
    private WebDriver driver;
    private Actions actions;  // ✅ Declare Actions as a class-level variable

    public HomePageActions(HomePage homePage, WebDriver driver) 
    {
        this.homePage = homePage;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUtils = new WaitUtils(driver);
        this.actions = new Actions(driver);  // ✅ Initialize Actions object here
    }

    public void searchLocation(String location) throws InterruptedException, AWTException
    { 
        waitUtils.waitForElementVisible(homePage.txt_search, 10);

        homePage.txt_search.sendKeys(location);
        Thread.sleep(2000);
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(4000);
/*
        // ✅ Use class-level Actions instance
        actions.sendKeys(Keys.DOWN)
               .sendKeys(Keys.ENTER)
               .perform();*/
    }
    
   
}
