package tqs.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "id")
    private String ticketId;

    @Column(name = "cardType")
    private String cardType;

    @Column(name = "cardNumber")
    private int cardNumber;

    @Column(name = "cardMonth") 
    private int cardMonth;

    @Column(name = "cardYear")
    private int cardYear;

    @Column(name = "cardCvv")
    private int cardCvv;

    @Column(name = "personid")
    private int personid;

    @Column(name = "routeid")
    private int routeid;
    

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personid", referencedColumnName = "id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routeid", referencedColumnName = "id", insertable = false, updatable = false)
    private Route route;

    public String getId() {
        return ticketId;
    }

    public void setId(String ticketId) {
        this.ticketId = ticketId;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }
    

}
