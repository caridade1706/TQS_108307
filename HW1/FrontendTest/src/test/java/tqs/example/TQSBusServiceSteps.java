package tqs.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.*;

public class TQSBusServiceSteps {
    private WebDriver driver;
    JavascriptExecutor js;
    private String reservationId;

    @Given("I am on the bus service homepage")
    public void i_am_on_the_bus_service_homepage() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.get("http://localhost:5173/");
    }

    @When("I enter {string} as the origin")
    public void i_enter_as_the_origin(String origin) {
        driver.findElement(By.id("origin")).sendKeys(origin);
    }

    @And("I enter {string} as the destination")
    public void i_enter_as_the_destination(String destination) {
        driver.findElement(By.id("destination")).sendKeys(destination);
    }

    @And("I select the travel date as {string}")
    public void i_enter_as_the_date(String date) {
        driver.findElement(By.name("date")).sendKeys(date);
    }

    @And("I change the currency")
    public void i_change_currency(){
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.cssSelector("li:nth-child(5) > .dropdown-item")).click(); 
    }


    @Then("I click the {string} button")
    public void i_click_on_the_button_Search(String buttonText) {
        WebElement purchaseButton = driver.findElement(By.xpath("//button[contains(text(), '" + buttonText + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", purchaseButton);
    }


    @When("I select the route with the index {string}")
    public void select_the_route_with_the_index(String index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-" + index)));
        button.click();
    }

    @And("I fill in the passenger details")
    public void fill_in_the_passenger_details() {
        driver.findElement(By.id("name")).sendKeys("Tiago Gomes");
        driver.findElement(By.id("email")).sendKeys("teste@teste.com");
        WebElement phoneInput = driver.findElement(By.id("phone"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneInput);
        phoneInput.sendKeys("12345689");
        WebElement addressInput = driver.findElement(By.id("address"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressInput);
        addressInput.sendKeys("Rua da universidade");
        WebElement cityInput = driver.findElement(By.id("city"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityInput);
        cityInput.sendKeys("Barcelos");
        WebElement zipInput = driver.findElement(By.id("zip"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", zipInput);
        zipInput.sendKeys("1234-567");
    }
    
    @And("I fill in the payment details")
    public void fill_in_the_payment_details() {
        WebElement cardNumberInput = driver.findElement(By.id("cardNumber"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cardNumberInput);
        cardNumberInput.sendKeys("1234567890");
        WebElement cardMonthInput = driver.findElement(By.id("cardMonth"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cardMonthInput);
        cardMonthInput.sendKeys("12");
        WebElement cardYearInput = driver.findElement(By.id("cardYear"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cardYearInput);
        cardYearInput.sendKeys("2025");
        WebElement cardCvvInput = driver.findElement(By.id("cardCvv"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cardCvvInput);
        cardCvvInput.sendKeys("123");
    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait1.until(ExpectedConditions.alertIsPresent());
        String[] alertText = alert.getText().split(":");
        alert.accept();
        reservationId = alertText[1].replaceAll("\\s", "");
    }

    @And("I check the reservation")
    public void i_click_the_to_check_my_reservations() {
        driver.findElement(By.linkText("Tickets")).click();
        driver.findElement(By.id("reservationId")).click();
        driver.findElement(By.id("reservationId")).sendKeys(reservationId);
        driver.findElement(By.cssSelector("button:nth-child(3)")).click();
        js.executeScript("window.scrollTo(0,0)");
    }

    @Then("I go back the homepage")
    public void i_go_back_the_homepage() {
        driver.findElement(By.linkText("Bus Ticketing Service")).click();
    }


    @io.cucumber.java.After
    public void tearDown() {
        driver.quit();
    }
}
