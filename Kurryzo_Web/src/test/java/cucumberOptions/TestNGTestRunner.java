package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.CucumberListener;
import utils.Retry;

@CucumberOptions(
    features = "src/test/java/features",
    glue = "stepDefinitions",
    monochrome = true,
    		 //  dryRun=true,
  //  tags = "@PlaceOrder1 or @OffersPage1 or @Smoke",
    	    tags = "@SmokeSSSSS",

    plugin = {
        "html:target/cucumber.html",
        "json:target/cucumber.json",
        "com.aventstack.extentreport"
        + "s.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/failed_scenarios.txt"
    }
)

@Listeners({CucumberListener.class})
//@Test(retryAnalyzer = Retry.class)  // Enable RetryAnalyzer for flaky tests
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

