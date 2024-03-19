Feature: BlazeDemo

    Background:
        Given I am on the BlazeDemo home page on "https://blazedemo.com/"

    Scenario: Selecting a flight
        When I select "Philadelphia" as the departure city and "Buenos Aires" as the destination city
        And I click on the Find Flights button
        And I choose the flight number 3
        And I complete the form with the following information:
            # | inputName        | address          | city    | state | zipCode | creditCardNumber | nameOnCard | 
            # | Tiago Gomes    | Travessa da Cabine nº73, Manhente  | Barcelos   | Continente    | 4750-557    | 123456789      | Tiago Gomes   | 

            | inputName | Tiago Gomes|
            | address | Travessa da Cabine nº73, Manhente |
            | city | Barcelos |
            | state | Continente |
            | zipCode | 4750-557 |
            | creditCardNumber | 123456789 |
            | nameOnCard | Tiago Gomes |


        And I do click on the Remember me checkbox
        And I click on the Purchase Flight button
        Then I should see the message "BlazeDemo Confirmation"
