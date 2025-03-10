package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class GenericUtils {
    
    private WebDriver driver;
    private JavascriptExecutor js;
    
    public GenericUtils(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    /**
     * Switches the WebDriver to the first child window that appears after opening a new window.
     * Thread-safe for parallel execution.
     */
    public void switchWindowToChild() 
    {
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();

        // Get the parent window handle
        String parentWindow = iterator.next();
        
        // Get the child window handle (the second window opened)
        String childWindow = iterator.hasNext() ? iterator.next() : null;

        // Switch to the child window if it exists
        if (childWindow != null) {
            driver.switchTo().window(childWindow);
        }
    }

    /**
     * Switches WebDriver back to the parent window.
     */
    public void switchWindowToParent() {
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();

        // Assuming the first window is the parent
        if (iterator.hasNext()) {
            String parentWindow = iterator.next();
            driver.switchTo().window(parentWindow);
        }
    }
    
    public void openNewTab(){
        js.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

    }
  
}
