package actions;

import java.awt.AWTException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cartUtiils.CommonReusableMethods;
import pageObjects.MenuPage;
import utils.BrowserActions;
import utils.FormatUtils;
import utils.TestContextSetup;
import utils.WaitUtils;

public class MenuPageActions {
    private MenuPage menuPage;
    private WebDriver driver;
    private WaitUtils waitUtils;
    private BrowserActions browserActions;
    CommonReusableMethods  commonMethods;
    TestContextSetup testContextSetup;
//	private JSONObject objDailyMenu;

    // Constructor
    public MenuPageActions(MenuPage menuPage,WebDriver driver) {
      this.driver = driver;
        this.menuPage = new MenuPage(driver);  // Initialize MenuPage inside MenuPageActions
        this.waitUtils = new WaitUtils(driver);
        this.browserActions = new BrowserActions(driver);
        commonMethods = new CommonReusableMethods(testContextSetup);
	//	this.objDailyMenu = testContextSetup.getObjDailyMenu(); // ✅ Get JSON from TestContextSetup

    }

    // ✅ Search menu item
    public void searchMenuItem(String menuName) throws InterruptedException 
    {
    	 Thread.sleep(2000);
    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	 waitUtils.waitForElementVisible(menuPage.txt_itemsearch, 20);
        browserActions.clearElement(menuPage.txt_itemsearch);
        browserActions.enterText(menuPage.txt_itemsearch, menuName);
        browserActions.scrollPage(0, 600);
        Thread.sleep(2000);

    }
    // ✅ Get item WebElement by name
    public void selecDeliveryType() 
    
    {
    	menuPage.btn_Delivery.click();
    }

    // ✅ Get item WebElement by name
    public WebElement getItemByName(String itemName) 
    {
        return menuPage.getItemByName(itemName);
    }
    
    public List<WebElement> getTotalRows() 
    {
        return menuPage.totalrow;  // Returning List<WebElement>
    }

    // ✅ Get item price by index
    public WebElement getPriceElementByIndex(int index) {
        return menuPage.getPriceElementByIndex(index);
    }

    // ✅ Get add-to-cart button for a specific item
    public WebElement getAddToCartButton(int index)
    {
        return menuPage.getAddToCartButton(index);
    }

    // ✅ Get modal popup element need to check model pup in menupage
    
    public WebElement getModalPopup()
    {
        return menuPage.menupopup;
    }

    // ✅ Get modal popup item name
    public WebElement getModalPopupItemName(String itemName) {
        return menuPage.getModalPopupItemName(itemName);
    }

    // ✅ Get serving size element
    public WebElement getServingSizeElement(String servingSize) {
        return menuPage.getServingSizeElement(servingSize);
    }

    // ✅ Get radio button for serving size
    public WebElement getRadioButton(String servingSize)
    {
        return menuPage.getRadioButton(servingSize);
    }

    // ✅ Get add button in the modal
    public WebElement getAddButton()
    {
        return menuPage.getAddButton();
    }

    
    public String getTotalCartItemCount() 
    {
        return menuPage.lbl_ItemCount.getText();
    }
    
    public String getCartSubTotal() 
    {
        return menuPage.lbl_TtotalAmount.getText();
    }
//Dynamic cart row eleements
    // Get Item Name
    public String getCartItemNameElement(int rowIndex) 
    {
    	
    return menuPage.getCartRowItemNameByIndex(rowIndex).getText();
    
    }

    // Get Item Price
    public String getCartRowPriceElement(int rowIndex) throws InterruptedException 
    
    {
    	waitUtils.waitForVisibility(menuPage.getCartRowPriceElementByIndex(rowIndex), 10);
    	return menuPage.getCartRowPriceElementByIndex(rowIndex).getText();
    }

    //  Get Serving Size
    public String getCartRowServingSizeElement(int rowIndex)
    {
        return menuPage.getCartRowServingSizeElementByIndex(rowIndex).getText();
    }

    //  Get Serving Total Price
    public String getCartRowServingTotalPriceElement(int rowIndex) 
    {
        return menuPage.getCartRowServingTotalPriceElementByIndex(rowIndex).getText();
    }

    //  Get Total Quantity
    public String getCarRowTotalQtyElement(int rowIndex) 
  {
        return menuPage.getCartRowTotalQtyElementByIndex(rowIndex).getText();
  }
    
    

    // ✅ Proceed to checkout
    public void checkout() 
    {
    	browserActions.scrollToElement(menuPage.chekOut);
        menuPage.chekOut.click();
    }

    // ✅ Click on an element with retries
    public void clickElementWithRetry(WebElement element)
    {
        boolean clicked = false;
        int attempts = 0;

        while (!clicked && attempts < 3) 
        {
            try {
            	browserActions.scrollToElementAndClick(element, 10);
                clicked = true;
            } catch (Exception e) {
                browserActions.scrollPage(0, 150);
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted during retry.", ex);
                }
            }
        }

        if (!clicked) {
            throw new RuntimeException("Failed to click element after 7 attempts.");
        }
    }

    // ✅ Handle modal actions (e.g., handling add button in a modal)
    public void handleModalActions(WebElement addButton) {
        clickElementWithRetry(addButton);
    }
    
	public void ClickOrderAhead() {
		menuPage.ddl_ordernow.click();
		menuPage.radio_Orderahead.click();
	}

	public void setRetailOrderAheadTime(String time) throws AWTException, InterruptedException {
		List<WebElement> timePickerAll = menuPage.timeLists;
		boolean timeFound = false;
		int maxScrollAttempts = 7; // Maximum number of scrolls to avoid infinite loops
		int scrollAttempts = 0; // Counter for scroll attempts

		while (!timeFound && scrollAttempts < maxScrollAttempts) {
			for (WebElement webElement : timePickerAll) {
				String listoFTime = webElement.getText();
				if (listoFTime.contains(time)) {
					// System.out.println("time" + time);
					Thread.sleep(800);
					driver.findElement(By.xpath("(//*[contains(text(),'" + time + "')])[6]")).click();
					timeFound = true;
					break;
				}

				if (!timeFound) {
					// System.out.println("Scroll clicking");

					Thread.sleep(800);

					driver.findElement(By.xpath("(//button[@class='xdsoft_next'])[6]")).click();

					// browser.findElement(By.xpath("(//div[@class='xdsoft_scroller'])[9]")).click();

					scrollAttempts++;
				}
			}
		}
		if (!timeFound) {
			System.out.println("Expected time not found after " + scrollAttempts
					+ " scroll attempts. and Not Found Time is " + time + "");
		}

	}


	
	public void selectOrderAheadTime(JSONObject objDailyMenu,String storeName) throws InterruptedException, AWTException, ParseException {
		menuPage.date_OrderAhead.click();
		Thread.sleep(200);
		String orderAheadTimeJson = objDailyMenu.getJSONObject("storeDetails").getJSONObject(storeName).getString("OrderAheadTime1");

		//String orderAheadTimeJson = objDailyMenu.getString("OrderAheadTime1");
		setRetailOrderAheadTime(orderAheadTimeJson);
		menuPage.popup_timeSubmitButton.click();

		// Menu Page Order ahead date and time
		String actorderAheadDateAndTime = menuPage.lbl_DateAndTime.getAttribute("value");

		// Current date
		String currentDate = String.valueOf(LocalDate.now());
		SimpleDateFormat currentDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat newdate = new SimpleDateFormat("dd/MM/yyyy");
		Date date = currentDateFormatter.parse(currentDate);
		// formatted Date
		String formattedDate = newdate.format(date);
		String expOrderAheadDateAndTime = formattedDate.concat(" " + orderAheadTimeJson);
		Assert.assertEquals(actorderAheadDateAndTime, expOrderAheadDateAndTime);
	}
	
	public void clickPartyOrderPopupCheckout() {
		menuPage.popup_checkoutPartyOrder.click();
		menuPage.popup_partyOrderHeadCountOk.click();
	}
	
	public void clickPartyOrderDeliveryMode() {
		menuPage.ddl_dispatchMode.click();
		menuPage.ddl_deliveryMode.click(); 
	}

}
