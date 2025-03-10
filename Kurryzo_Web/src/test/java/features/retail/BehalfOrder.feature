Feature:   Behalf Order 

  Background: 
    Given I Enter the Bo url and login as supervisor

  @Smoke1
  Scenario: Place the retail Behalf pickup order without discount with order now option
    And I use the Poovarasan customer login to login for Behalf pickup order now
    And I search for the location for Behalf pickup order now
    Then I search for the store for Behalf pickup order now
    Then I open the store for Behalf pickup order now
    And I Search and add the menu for Behalf pickup order now
    And I click on Checkout button in menu page for Behalf pickup order now
    Then verify order summary on checkout page without coupon discount for daily menu pickup order for Behalf pickup order now
    And Make a payment for Behalf pickup order now
    Then verify the order confirmation details for pickup order for Behalf pickup order now