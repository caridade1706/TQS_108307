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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public int getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    public int getCardMonth() {
        return cardMonth;
    }
    public void setCardMonth(int cardMonth) {
        this.cardMonth = cardMonth;
    }
    public int getCardYear() {
        return cardYear;
    }
    public void setCardYear(int cardYear) {
        this.cardYear = cardYear;
    }
    public int getCardCvv() {
        return cardCvv;
    }
    public void setCardCvv(int cardCvv) {
        this.cardCvv = cardCvv;
    }


    

    

    
}
