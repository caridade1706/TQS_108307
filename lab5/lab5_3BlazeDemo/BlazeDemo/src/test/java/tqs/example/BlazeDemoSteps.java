package tqs.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BlazeDemoSteps {
    WebDriver driver = new ChromeDriver();

    @Given("I am on the BlazeDemo home page on {string}")
    public void i_am_on_the_blazedemo_home_page_on(String url) {
        driver.get(url);
    }

    @When("I select {string} as the departure city and {string} as the destination city")
    public void i_select_as_the_departure_city_and_as_the_destination_city(String departureCity, String destinationCity) {
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.xpath("//option[. = '" + departureCity + "']")).click();
        
    }

    @And("I click on the Find Flights button")
    public void i_click_on_the_button() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the flight number {int}")
    public void i_choose_flight_number(Integer number) {
        driver.findElement(By.cssSelector("tr:nth-child("+ number +") .btn")).click();
    }

    @And("I complete the form with the following information:")
    public void i_fill_in_the_passenger_details_with_the_following_information(DataTable dataTable) {
        // dataTable.asMap(String.class, String.class).forEach((field, value) -> {
        //     driver.findElement(By.id(field)).sendKeys(value);
        // });

        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            driver.findElement(By.id(entry.getKey())).sendKeys(entry.getValue());
        }

        // driver.findElement(By.id("inputName")).sendKeys(dataTable.get("inputName"));
    }

    @And("I do click on the Remember me checkbox")
    public void i_ensure_the_checkbox_is_not_selected() {
        driver.findElement(By.id("rememberMe")).click();        
    }

    @And("I click on the Purchase Flight button")
    public void i_click_on_the_purchase_flight_button() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_page(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
    }
}
