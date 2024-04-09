package tqs.example.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private int routeid;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String zip;
    private String city;
    private String cardType;
    private int cardNumber;
    private int cardMonth;
    private int cardYear;
    private int cardCvv;

    public int getRouteid() {
        return routeid;
    }
    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }
    public String getNameDTO() {
        return name;
    }
    public void setNameDTO(String name) {
        this.name = name;
    }
    public String getEmailDTO() {
        return email;
    }
    public void setEmailDTO(String email) {
        this.email = email;
    }
    public String getPhoneDTO() {
        return phone;
    }
    public void setPhoneDTO(String phone) {
        this.phone = phone;
    }
    public String getAddressDTO() {
        return address;
    }
    public void setAddressDTO(String address) {
        this.address = address;
    }
    public String getZipDTO() {
        return zip;
    }
    public void setZipDTO(String zip) {
        this.zip = zip;
    }
    public String getCityDTO() {
        return city;
    }
    public void setCityDTO(String city) {
        this.city = city;
    }
    public String getCardTypeDTO() {
        return cardType;
    }
    public void setCardTypeDTO(String cardType) {
        this.cardType = cardType;
    }
    public int getCardNumberDTO() {
        return cardNumber;
    }
    public void setCardNumberDTO(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public int getCardMonthDTO() {
        return cardMonth;
    }
    public void setCardMonthDTO(int cardMonth) {
        this.cardMonth = cardMonth;
    }
    public int getCardYearDTO() {
        return cardYear;
    }
    public void setCardYearDTO(int cardYear) {
        this.cardYear = cardYear;
    }
    public int getCardCvvDTO() {
        return cardCvv;
    }
    public void setCardCvvDTO(int cardCvv) {
        this.cardCvv = cardCvv;
    }


    

    

    
}
