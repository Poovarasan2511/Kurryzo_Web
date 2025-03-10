package cartUtiils;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import utils.ReadJson;
import utils.TestContextSetup;

public class CartTest
{
	/*private static TestContextSetup testContextSetup;
    public static void main(String[] args) throws IOException 
    {
     //  WebDriver driver = null;
      
       
       try { 
		if (testContextSetup == null) {
		        testContextSetup = new TestContextSetup();  // Initialize it if it's null
		    } else {
		        testContextSetup = testContextSetup;
		    }
	} 
       catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		// WebDriver driver = null;  // Initialize WebDriver as required
        AddToCart addToCart = new AddToCart(testContextSetup);
        
        // Read the JSON file
       // JSONObject menuDetails = ReadJson.setJson("path/to/dailyMenu.json"); // Replace with actual file path
        //List<MenuItem> menuItems = addToCart.getMenuItems(menuDetails.getJSONObject("menuDetails"), "Rambhavan Test");
        JSONObject menuDetails = ReadJson.setJson("D:\\Automation\\CucumberFramework_new\\CucumberFramework\\src\\test\\java\\testDataTypes\\", "dailyMenu.json"); // Update with actual file path
    	List<MenuItem> menuItems = addToCart.getMenuItems(menuDetails.getJSONObject("menuDetails"), "Rambhavan Test");
       
        Cart cart = new Cart();
        
        // Adding items to the cart
        for (MenuItem menuItem : menuItems) 
        {
        	String name=menuItem.getName();
        	int qty=menuItem.getQty();
        	 System.out.println("Detailed ---namet:\n" + name);
             System.out.println("-----qtt:\n" + qty);
            cart.addOrUpdateItem(menuItem);
        }

        // Output the detailed and summary cart information
        System.out.println("Detailed Cart Output:\n" + cart.getDetailedCartOutput());
        System.out.println("Summary Cart Output:\n" + cart.getSummaryCartOutput());
    }*/
}
