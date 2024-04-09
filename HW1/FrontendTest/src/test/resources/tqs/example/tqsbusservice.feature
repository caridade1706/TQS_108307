Feature: Bus Ticket Booking Service

  Scenario: Book a bus ticket from Braga to Porto
    Given I am on the bus service homepage
    When I enter "Braga" as the origin
    And I enter "Porto" as the destination
    And I select the travel date as "01-04-2024"
    And I change the currency
    Then I click the "Find Routes" button

    When I select the route with the index "1"
    And I fill in the passenger details
    And I fill in the payment details
    And I click the "Purchase Ticket" button
    And I should see a confirmation message
    And I check the reservation
    Then I go back the homepage

  


    
