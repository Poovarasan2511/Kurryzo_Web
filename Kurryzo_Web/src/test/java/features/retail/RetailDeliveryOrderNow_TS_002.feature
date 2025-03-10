Feature:   Retail Delivery Feature Order Now

  Background: 
    Given Open Browser and enter customer url
    When I click on Login Button in store list page

  @Smoke
  Scenario: Place the retail deivery order without discount with order now option
    When I search for the store location "Rambhavan Test"
    When I search for a store named "Rambhavan Test"
    And I select the store "Rambhavan Test"
    When I  change delivery as daily menu delivery mode
    And I add the menu items for "Rambhavan Test" to the cart
    And I verify the cart information for the store named "Rambhavan Test"
    And I proceed to checkout
    Then I verify the order summary without discount for daily menu pickup from "Rambhavan Test"
    And the user selects the following delivery address:
      """
      45,LB Road Adyar,
      Adyar, 
      Chennai- 600020.
      """
    Then i see the dunzo delivery charge charge for 85.00
    Then I verify the order summary without discount for daily menu delivery order from "Rambhavan Test"
    And I make a payment for "Rambhavan Test"
    Then I verify the order confirmation details for the "Rambhavan Test" delivery order
    Then verify the adyar deivery address and deivery charge  in order confirmation page
    And I login to BO and switch to the store "Rambhavan Test" then open the order and get the status for Retail Delivery Now without discount
    Then I Open new incognito browser and Login as Supervisor and open the order for "Rambhavan Test" for Retail Delivery Now without discount
    Then I confirm the order for "Rambhavan Test" for Retail Delivery Now without discount
    Then I open the order in Preparation tab and compare the status in support dashboard for Retail Delivery Now without discount
    Then I click on Ready For Pickup and compare the status in support dashboard for Retail Delivery Now without discount
    Then I click on delivered and compare the status in support dashboard for Retail Delivery Now without discount
