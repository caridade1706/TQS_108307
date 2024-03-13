package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ChoseFlightPage {

    private WebDriver driver;

    //Page URL
   
    //Locators
    @FindBy(how = How.CSS, using = "tr:nth-child(3) .btn")
    private WebElement purchaseFlight;

    //Constructor
    public ChoseFlightPage(WebDriver driver){
        this.driver=driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Click on purchase flight button
    public void clickPurchaseFlight(){
        purchaseFlight.click();
    }
    
}
