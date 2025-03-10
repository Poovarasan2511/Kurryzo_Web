package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

public class AssertionUtils {

    private static final Logger logger = LogManager.getLogger(AssertionUtils.class);

    // Assertion to check if an element is displayed
    public void assertElementDisplayed(Boolean condition, String elementName) 
    {
        try {
            Assert.assertTrue(condition, elementName + " should be visible on the page.");
            logger.info(elementName + " is visible on the page.");
        } catch (AssertionError e) {
            logger.error("Assertion failed for: " + elementName, e);
            throw e;  // Re-throw the exception after logging
        }
    }

    public void assertTextEquals(String actualText, String expectedText) 
    {
        try {
            Assert.assertEquals(actualText, expectedText, "Text does not match the expected value.");
            logger.info(expectedText + " text matches the expected value.");
        } catch (AssertionError e) {
            logger.error("Assertion failed for: " + expectedText, e);
            throw e;
        }
    }


    // Assertion for page title
    public void assertPageTitle(String expectedTitle, WebDriver driver) {
        try {
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match the expected value.");
            logger.info("Page title matches the expected value: " + actualTitle);
        } catch (AssertionError e) {
            logger.error("Assertion failed: Page title does not match.", e);
            throw e;
        }
    }

    // Assertion to check if an element is clickable
    public void assertElementClickable(Boolean condition, String elementName) {
        try {
            Assert.assertTrue(condition, elementName + " should be clickable on the page.");
            logger.info(elementName + " is clickable.");
        } catch (AssertionError e) {
            logger.error("Assertion failed for: " + elementName, e);
            throw e;
        }
    }

    // Assertion for element visibility
    public void assertElementIsVisible(Boolean condition, String elementName) {
        try {
            Assert.assertTrue(condition, elementName + " should be visible.");
            logger.info(elementName + " is visible.");
        } catch (AssertionError e) {
            logger.error("Assertion failed for: " + elementName, e);
            throw e;
        }
    }

    // Asserts if the profile name is visible after login
    public void assertProfileNameVisible(Boolean isVisible) 
    {
        try {
            Assert.assertTrue(isVisible, "Profile Name should be visible after login.");
            logger.info("Profile name is visible.");
        } catch (AssertionError e) {
            logger.error("Assertion failed: Profile name not visible after login.", e);
            throw e;
        }
    }
    



    // Method to check if the object is either null or not null based on the expected value
    public void assertNullOrNotNull(Object object, boolean expectedNotNull, String errorMessage) 
    {
        try {
            if (expectedNotNull) {
                // Assert that the object is not null
                Assert.assertNotNull(object, errorMessage);
            } else {
                // Assert that the object is null
                Assert.assertNull(object, errorMessage);
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            throw e; // Re-throw to fail the test case
        }
    }

public void assertNotNull(Object object, boolean expectedNotNull, String errorMessage) {
    try {
        if (expectedNotNull) {
            // Assert that the object is not null
            Assert.assertNotNull(object, errorMessage);
        } else {
            // Assert that the object is null
            Assert.assertNull(object, errorMessage);
        }
    } catch (AssertionError e) {
        System.out.println("Assertion failed: " + e.getMessage());
        throw e; // Re-throw to fail the test case
    }
}

}
