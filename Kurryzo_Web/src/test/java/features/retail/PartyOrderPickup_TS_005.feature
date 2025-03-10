Feature: Party Order Pickup

  Background: 
    Given Open Browser and enter customer url
    When I click on Login Button in store list page

  @Smokeky
  Scenario: Party Order Pickup without coupon discount
    When I search for the store location "Rambhavan Test" for Party Order Pickup without coupon discount
        And I Click on party orders tab in store listing screen and set headcount and date and time
        When I search for a store named "Rambhavan Test" for Party Order Pickup without coupon discount
    
    And I select the store "Rambhavan Test" for Party Order Pickup without coupon discount
    And I add the menu items for "Rambhavan Test" to the cart for Party Order Pickup without coupon discount
    And I verify the cart information for the store named "Rambhavan Test" for Party Order Pickup without coupon discount
    And I proceed to checkout for Party Order Pickup without coupon discount
 #   Then I verify the order summary without discount for daily menu pickup from "Rambhavan Test" for Party Order Pickup without coupon discount
    And I make a payment for "Rambhavan Test" for Party Order Pickup without coupon discount
    Then I verify the order confirmation details for the "Rambhavan Test" pickup order for Party Order Pickup without coupon discount
    
    
    And I login to BO and switch to the store "Rambhavan Test" then open the order and get the status for Party Order Pickup without coupon discount
    Then I Open new incognito browser and Login as Supervisor and open the order for "Rambhavan Test" for Party Order Pickup without coupon discount
    Then I confirm the order for "Rambhavan Test" for Party Order Pickup without coupon discount
    Then I open the order in InProcess tab and compare the status in support dashboard for Party Order Pickup without coupon discount
    Then I open the order in Preparation tab and compare the status in support dashboard for Party Order Pickup without coupon discount
    Then I click on Ready For Pickup and compare the status in support dashboard for Party Order Pickup without coupon discount
    Then I click on delivered and compare the status in support dashboard for Party Order Pickup without coupon discount
