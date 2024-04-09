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
        

        ticket.setId("d20ab270-7c81-4087-a7a6-259b0aOcc3ea");
        ticket.setPerson(person);
        ticket.setPersonid(0);
        route.setOcupation(route.getOcupation() - 1);
        ticket.setRoute(route);
        ticket.setRouteid(0);
        ticket.setCardType("Visa");
        ticket.setCardNumber(123456789);
        ticket.setCardMonth(12);
        ticket.setCardYear(2023);
        ticket.setCardCvv(123);

    

        assertEquals("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", ticket.getId());
        assertEquals(person, ticket.getPerson());
        assertEquals(0, ticket.getPersonid());
        assertEquals(route, ticket.getRoute());
        assertEquals(0, ticket.getRouteid());
        assertEquals("Visa", ticket.getCardType());
        assertEquals(123456789, ticket.getCardNumber());
        assertEquals(12, ticket.getCardMonth());
        assertEquals(2023, ticket.getCardYear());
        assertEquals(123, ticket.getCardCvv());

    }

    @Test
    void testConstructor(){

        ticket = new Ticket("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", "Visa", 123456789, 12, 2023, 123,0,0, person, route);

        assertEquals("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", ticket.getId());
        assertEquals(person, ticket.getPerson());
        assertEquals(0, ticket.getPersonid());
        assertEquals(route, ticket.getRoute());
        assertEquals(0, ticket.getRouteid());
        assertEquals("Visa", ticket.getCardType());
        assertEquals(123456789, ticket.getCardNumber());
        assertEquals(12, ticket.getCardMonth());
        assertEquals(2023, ticket.getCardYear());
        assertEquals(123, ticket.getCardCvv());
        

    }

    @Test 
    void isBusFull(){
        assertNotEquals(0, route.getOcupation());
    }
}
