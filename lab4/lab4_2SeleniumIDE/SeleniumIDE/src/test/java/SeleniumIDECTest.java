
import io.github.bonigarcia.seljup.SeleniumJupiter;

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

@ExtendWith(SeleniumJupiter.class)
public class SeleniumIDECTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }public class SeliniumIDEbTest {
    
    }
    

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void seleniumIDETest() {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1920, 1053));

        // Selecting "Philadelphia" from the dropdown
        WebElement dropdown = driver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();

        // Clicking on the "Find Flights" button
        driver.findElement(By.cssSelector(".btn-primary")).click();

        // Choosing a flight and proceeding to the next page
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();

        // Filling out the form
        driver.findElement(By.id("inputName")).sendKeys("Tiago Gomes");
        driver.findElement(By.id("address")).sendKeys("Travessa da Cabine nº73, Manhente");
        driver.findElement(By.id("city")).sendKeys("Barcelos");

        // Asserting if the state input field is editable
        WebElement stateField = driver.findElement(By.id("state"));
        assertTrue(stateField.isEnabled() && stateField.getAttribute("readonly") == null);

        driver.findElement(By.id("state")).sendKeys("Continente");
        driver.findElement(By.id("zipCode")).sendKeys("4750-557");
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");

        // Asserting if the name on card field exists
        assertTrue(driver.findElements(By.id("nameOnCard")).size() > 0);

        driver.findElement(By.id("nameOnCard")).sendKeys("Tiago Gomes");

        // Clicking on the rememberMe checkbox
        driver.findElement(By.id("rememberMe")).click();
        assertTrue(driver.findElement(By.id("rememberMe")).isSelected());

        // Clicking on the "Purchase Flight" button
        driver.findElement(By.cssSelector(".btn-primary")).click();

        // Asserting the title of the confirmation page
        assertEquals("BlazeDemo Confirmation", driver.getTitle());

        // Navigating to another page
        driver.findElement(By.linkText("Travel The World")).click();
    }
}
