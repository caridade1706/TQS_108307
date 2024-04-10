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

    public String getIdTicket() {
        return ticketId;
    }

    public void setIdTicket(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCardTypeTicket() {
        return cardType;
    }

    public void setCardTypeTicket(String cardType) {
        this.cardType = cardType;
    }

    public int getCardNumberTicket() {
        return cardNumber;
    }

    public void setCardNumberTicket(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardMonthTicket() {
        return cardMonth;
    }

    public void setCardMonthTicket(int cardMonth) {
        this.cardMonth = cardMonth;
    }

    public int getCardYearTicket() {
        return cardYear;
    }

    public void setCardYearTicket(int cardYear) {
        this.cardYear = cardYear;
    }

    public int getCardCvvTicket() {
        return cardCvv;
    }

    public void setCardCvvTicket(int cardCvv) {
        this.cardCvv = cardCvv;
    }

    public Person getPersonTicket() {
        return person;
    }

    public void setPersonTicket(Person person) {
        this.person = person;
    }

    public Route getRouteTicket() {
        return route;
    }

    public void setRouteTicket(Route route) {
        this.route = route;
    }

    public int getPersonidTicket() {
        return personid;
    }

    public void setPersonidTicket(int personid) {
        this.personid = personid;
    }

    public int getRouteidTicket() {
        return routeid;
    }

    public void setRouteidTicket(int routeid) {
        this.routeid = routeid;
    }
    

}
