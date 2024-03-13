package test;
import webpages.HomePage;
import webpages.ChoseFlightPage;
import webpages.ReservationPage;
import webpages.ConfirmationPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PageObjectPatternTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test() {
        HomePage homePage = new HomePage(driver);
        homePage.setDepartureCity("Philadelphia");
        homePage.setDestinationCity("New York");
        homePage.clickFindFlights();
        ChoseFlightPage choseFlightPage = new ChoseFlightPage(driver);
        choseFlightPage.clickPurchaseFlight();
        ReservationPage reservationPage = new ReservationPage(driver);
        reservationPage.setInputName("Tiago Gomes");
        reservationPage.setAddress("Travessa da Cabine nº73, Manhente");
        reservationPage.setCity("Barcelos");
        reservationPage.setState("Continente");
        reservationPage.setZipCode("4750-557");
        reservationPage.setCreditCardNumber("123456789");
        reservationPage.setNameOnCard("Tiago Gomes");
        reservationPage.clickRememberMe();
        reservationPage.clickPurchaseFlight();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        assertEquals("BlazeDemo Confirmation", confirmationPage.getConfirmationHeader());
        confirmationPage.clickTravelTheWorldLink();
    }  
    
}

