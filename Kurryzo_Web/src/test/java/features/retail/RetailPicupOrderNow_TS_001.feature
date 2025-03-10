Feature: Retail Order Feature Order Now
Background:
 
  Given Open Browser and enter customer url
 When I click on Login Button in store list page

  	
  	
#@Smoke

 @Smoke
Scenario: Place the retail pickup order without discount with order now option

   When I search for the store location "Rambhavan Test"
   When I search for a store named "Rambhavan Test"
   And I select the store "Rambhavan Test"
  And I add the menu items for "Rambhavan Test" to the cart
  And I verify the cart information for the store named "Rambhavan Test" 
  And I proceed to checkout
  Then I verify the order summary without discount for daily menu pickup from "Rambhavan Test"
  And I make a payment for "Rambhavan Test"
  Then I verify the order confirmation details for the "Rambhavan Test" pickup order
   And I login to BO and switch to the store "Rambhavan Test" then open the order and get the status
 Then I Open new incognito browser and Login as Supervisor and open the order for "Rambhavan Test"
   Then I confirm the order for "Rambhavan Test"
  Then I open the order in Preparation tab and compare the status in support dashboard
   Then I click on Ready For Pickup and compare the status in support dashboard
   Then I click on delivered and compare the status in support dashboard


	 

	   
	  

	 
	 
	  
    

    

    
 