package utils;

import java.util.EventListener;

import org.testng.ITestListener;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberListener implements ITestListener  {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Before
    public void beforeScenario() {
        retryCount = 0;  // Reset the retry count before each scenario
    }

    @After
    public void afterScenario(Scenario scenario) 
    {
        if (scenario.isFailed()) {
            if (retryCount < MAX_RETRY_COUNT) {
                retryCount++;
                // Logic to re-execute the failed scenario (re-run the same scenario)
                System.out.println("Scenario failed, retrying... Retry count: " + retryCount);
            }
        }
    }
}
