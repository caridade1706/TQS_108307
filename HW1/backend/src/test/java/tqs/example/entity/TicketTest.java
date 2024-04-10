package tqs.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Bus bus;
    private Route route;
    private Ticket ticket;
    private Person person;

    @BeforeEach
    void setUp() {
        bus = new Bus("AA0000", 50, "TST", "Mercedes", 2021);
        route = new Route(0,"Porto", "Lisboa", "2021-05-01", "12:00", "14:00", 50, 100, 50,"AA0000", bus);
        person = new Person(0, "John Doe", "john@email.com", "123456789", "Street", "12345", "Seattle");
        ticket = new Ticket();

    }

    @Test
    void createATicket() {
        assertNotNull(ticket);
        

        ticket.setIdTicket("d20ab270-7c81-4087-a7a6-259b0aOcc3ea");
        ticket.setPersonTicket(person);
        ticket.setPersonidTicket(0);
        route.setOcupation(route.getOcupation() - 1);
        ticket.setRouteTicket(route);
        ticket.setRouteidTicket(0);
        ticket.setCardTypeTicket("Visa");
        ticket.setCardNumberTicket(123456789);
        ticket.setCardMonthTicket(12);
        ticket.setCardYearTicket(2023);
        ticket.setCardCvvTicket(123);

    

        assertEquals("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", ticket.getIdTicket());
        assertEquals(person, ticket.getPersonTicket());
        assertEquals(0, ticket.getPersonidTicket());
        assertEquals(route, ticket.getRouteTicket());
        assertEquals(0, ticket.getRouteidTicket());
        assertEquals("Visa", ticket.getCardTypeTicket());
        assertEquals(123456789, ticket.getCardNumberTicket());
        assertEquals(12, ticket.getCardMonthTicket());
        assertEquals(2023, ticket.getCardYearTicket());
        assertEquals(123, ticket.getCardCvvTicket());

    }

    @Test
    void testConstructor(){

        ticket = new Ticket("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", "Visa", 123456789, 12, 2023, 123,0,0, person, route);

        assertEquals("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", ticket.getIdTicket());
        assertEquals(person, ticket.getPersonTicket());
        assertEquals(0, ticket.getPersonidTicket());
        assertEquals(route, ticket.getRouteTicket());
        assertEquals(0, ticket.getRouteidTicket());
        assertEquals("Visa", ticket.getCardTypeTicket());
        assertEquals(123456789, ticket.getCardNumberTicket());
        assertEquals(12, ticket.getCardMonthTicket());
        assertEquals(2023, ticket.getCardYearTicket());
        assertEquals(123, ticket.getCardCvvTicket());
        

    }

    @Test 
    void isBusFull(){
        assertNotEquals(0, route.getOcupation());
    }
}
