package test;

import static io.github.bonigarcia.seljup.BrowserType.CHROME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import webpages.ChoseFlightPage;
import webpages.ConfirmationPage;
import webpages.HomePage;
import webpages.ReservationPage;

@ExtendWith(SeleniumJupiter.class)
public class PageObjectPatternDockerTest {

    @Test
    public void testDockerChromeLab4_4(@DockerBrowser(type = CHROME) WebDriver driver){
        

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