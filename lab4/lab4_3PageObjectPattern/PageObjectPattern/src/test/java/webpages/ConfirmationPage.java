package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    private WebDriver driver;

    //Page URL
   

    //Locators

    @FindBy(how= How.LINK_TEXT, using = "Travel The World")
    private WebElement travelTheWorldLink;

    //Constructor
    public ConfirmationPage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Get confirmation header
    public String getConfirmationHeader(){
        return driver.getTitle();
    }

    //Click on travel the world link
    public void clickTravelTheWorldLink(){
        travelTheWorldLink.click();
    }
    
}
