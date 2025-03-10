package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utils.TestContextSetup;

public class Hooks {

	

	    private TestContextSetup testContextSetup;

	    public Hooks(TestContextSetup testContextSetup) {
	        this.testContextSetup = testContextSetup;
	    }

	   /* @Before
	    public void beforeScenario() {
	        String browser = System.getProperty("browser", "chrome");  // Default to chrome if not specified
	        testContextSetup = new TestContextSetup(browser);
	    }*/
	    @Before
	    public void beforeScenario(Scenario scenario) {
	        System.out.println("Starting scenario: " + scenario.getName());
	    }
	    @AfterStep
	    public void addScreenshot(Scenario scenario) {
	        if (scenario.isFailed()) {
	            // Capture screenshot on failure
	            byte[] screenshot = ((TakesScreenshot) testContextSetup.getDriver()).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", "Failure Screenshot");
	        }
	    }

	    @After
	    public void afterScenario() {
	        testContextSetup.cleanup();
	        System.out.println("Ending scenario.");
	    }
	}

