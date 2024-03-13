package webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReservationPage {
    private WebDriver driver;
    
    //Page URL
  

    @FindBy(how = How.ID, using = "inputName")
        private WebElement inputName;

    @FindBy(how = How.ID, using = "address")
        private WebElement address;

    @FindBy(how = How.ID, using = "city")
        private WebElement city;

    @FindBy(how = How.ID, using = "state")
        private WebElement state;
    
    @FindBy(how = How.ID, using = "zipCode")
        private WebElement zipCode;
    
    @FindBy(how = How.ID, using = "creditCardNumber")
        private WebElement creditCardNumber;

    @FindBy(how = How.ID, using = "nameOnCard")
        private WebElement nameOnCard;

    @FindBy(how = How.ID, using = "rememberMe")
        private WebElement rememberMe;

    @FindBy(how = How.CSS, using = ".btn-primary")
        private WebElement purchaseFlight;

    //Constructor

    public ReservationPage(WebDriver driver){
        this.driver=driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    

    //Set inputName
    public void setInputName(String name){
        inputName.sendKeys(name);
    }

    //Set address
    public void setAddress(String address){
        this.address.sendKeys(address);
    }

    //Set city
    public void setCity(String city){
        this.city.sendKeys(city);
    }

    //Set state
    public void setState(String state){
        this.state.sendKeys(state);
    }

    //Set zipCode
    public void setZipCode(String zipCode){
        this.zipCode.sendKeys(zipCode);
    }

    //Set creditCardNumber
    public void setCreditCardNumber(String creditCardNumber){
        this.creditCardNumber.sendKeys(creditCardNumber);
    }

    //Set nameOnCard
    public void setNameOnCard(String nameOnCard){
        this.nameOnCard.sendKeys(nameOnCard);
    }

    //Click on rememberMe
    public void clickRememberMe(){
        rememberMe.click();
    }

    //Click on purchaseFlight
    public void clickPurchaseFlight(){
        purchaseFlight.click();
    }

}

