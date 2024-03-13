package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/";

    //Locators
    @FindBy(how = How.NAME, using = "fromPort")
    private WebElement fromPort;

    @FindBy(how = How.NAME, using = "toPort")
    private WebElement toPort;

    @FindBy(how = How.CSS, using = ".btn-primary")
    private WebElement findFlights;

    //Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Set departure city
    public void setDepartureCity(String departureCity){
        fromPort.sendKeys(departureCity);
    }

    //Set destination city

    public void setDestinationCity(String destinationCity){
        toPort.sendKeys(destinationCity);
    }

    //Click on find flights button
    public void clickFindFlights(){
        findFlights.click();
    }

  

}