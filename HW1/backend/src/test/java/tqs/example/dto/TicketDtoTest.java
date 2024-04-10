package tqs.example.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TicketDtoTest {

    private TicketDTO ticket;

    @BeforeEach
    void setUp() {
        ticket = new TicketDTO();
    }

    @Test
    void createATicket() {
        assertNotNull(ticket);

        ticket.setRouteid(0);
        ticket.setName("John Doe");
        ticket.setEmail("john@email.com");
        ticket.setPhone("123456789");
        ticket.setAddress("Rua do ISEP");
        ticket.setZip("4200-072");
        ticket.setCity("Porto");
        ticket.setCardType("Visa");
        ticket.setCardNumber(123456789);
        ticket.setCardMonth(12);
        ticket.setCardYear(2023);
        ticket.setCardCvv(123);

        assertEquals(0, ticket.getRouteid());
        assertEquals("John Doe", ticket.getName());
        assertEquals("john@email.com", ticket.getEmail());
        assertEquals("123456789", ticket.getPhone());
        assertEquals("Rua do ISEP", ticket.getAddress());
        assertEquals("4200-072", ticket.getZip());
        assertEquals("Porto", ticket.getCity());
        assertEquals("Visa", ticket.getCardType());
        assertEquals(123456789, ticket.getCardNumber());
        assertEquals(12, ticket.getCardMonth());
        assertEquals(2023, ticket.getCardYear());
        assertEquals(123, ticket.getCardCvv());
    }   
    
    @Test
    void testConstructor(){

        ticket = new TicketDTO(0, "John Doe", "john@email.com",
                "123456789", "Rua do ISEP", "4200-072", "Porto",
                "Visa", 123456789, 12, 2023, 123);
        
        assertEquals(0, ticket.getRouteid());
        assertEquals("John Doe", ticket.getName());
        assertEquals("john@email.com", ticket.getEmail());
        assertEquals("123456789", ticket.getPhone());
        assertEquals("Rua do ISEP", ticket.getAddress());
        assertEquals("4200-072", ticket.getZip());
        assertEquals("Porto", ticket.getCity());
        assertEquals("Visa", ticket.getCardType());
        assertEquals(123456789, ticket.getCardNumber());
        assertEquals(12, ticket.getCardMonth());
        assertEquals(2023, ticket.getCardYear());
        assertEquals(123, ticket.getCardCvv());
    }
}
