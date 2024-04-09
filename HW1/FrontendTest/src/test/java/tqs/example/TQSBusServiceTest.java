package tqs.example;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;

public class TQSBusServiceTest {
  private WebDriver driver;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void tQSBusService() {
    driver.get("http://localhost:5173/");
    driver.manage().window().setSize(new Dimension(1920, 1053));
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector("li:nth-child(5) > .dropdown-item")).click();
    driver.findElement(By.id("origin")).click();
    driver.findElement(By.id("origin")).sendKeys("Braga");
    driver.findElement(By.id("destination")).click();
    driver.findElement(By.id("destination")).sendKeys("Porto");
    driver.findElement(By.name("date")).click();
    driver.findElement(By.name("date")).sendKeys("01-04-2024");
    driver.findElement(By.xpath("//button[contains(text(), 'Find Routes')]")).click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    WebElement bookNowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1")));
    bookNowButton.click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("Tiago Gomes");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("teste@teste.com");
    WebElement phoneInput = driver.findElement(By.id("phone"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", phoneInput);
    phoneInput.sendKeys("123456789");
    WebElement addressInput = driver.findElement(By.id("address"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressInput);
    addressInput.sendKeys("Rua da universidade");
    WebElement cityInput = driver.findElement(By.id("city"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityInput);
    cityInput.sendKeys("Barcelos");
    WebElement zipInput = driver.findElement(By.id("zip"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", zipInput);
    zipInput.sendKeys("1234-567");
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
    WebElement purchaseButton = driver.findElement(By.xpath("//button[contains(text(), 'Purchase Ticket')]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", purchaseButton);
    // After clicking the purchase button and before any other action
    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
    Alert alert = wait1.until(ExpectedConditions.alertIsPresent());

    String[] alertText = alert.getText().split(":");
    alert.accept();
    String reservationId = alertText[1].replaceAll("\\s", "");

  
    WebElement element = driver.findElement(By.xpath("//button[contains(text(), 'OK')]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
    element);

  

    driver.findElement(By.linkText("Tickets")).click();
    driver.findElement(By.id("reservationId")).click();
    driver.findElement(By.id("reservationId")).sendKeys(reservationId);
    driver.findElement(By.cssSelector("button:nth-child(3)")).click();
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.linkText("Bus Ticketing Service")).click();
    driver.quit();
  }
}
