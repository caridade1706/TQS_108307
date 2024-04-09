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
        ticket.setNameDTO("John Doe");
        ticket.setEmailDTO("john@email.com");
        ticket.setPhoneDTO("123456789");
        ticket.setAddressDTO("Rua do ISEP");
        ticket.setZipDTO("4200-072");
        ticket.setCityDTO("Porto");
        ticket.setCardTypeDTO("Visa");
        ticket.setCardNumberDTO(123456789);
        ticket.setCardMonthDTO(12);
        ticket.setCardYearDTO(2023);
        ticket.setCardCvvDTO(123);

        assertEquals(0, ticket.getRouteid());
        assertEquals("John Doe", ticket.getNameDTO());
        assertEquals("john@email.com", ticket.getEmailDTO());
        assertEquals("123456789", ticket.getPhoneDTO());
        assertEquals("Rua do ISEP", ticket.getAddressDTO());
        assertEquals("4200-072", ticket.getZipDTO());
        assertEquals("Porto", ticket.getCityDTO());
        assertEquals("Visa", ticket.getCardTypeDTO());
        assertEquals(123456789, ticket.getCardNumberDTO());
        assertEquals(12, ticket.getCardMonthDTO());
        assertEquals(2023, ticket.getCardYearDTO());
        assertEquals(123, ticket.getCardCvvDTO());
    }   
    
    @Test
    void testConstructor(){

        ticket = new TicketDTO(0, "John Doe", "john@email.com",
                "123456789", "Rua do ISEP", "4200-072", "Porto",
                "Visa", 123456789, 12, 2023, 123);
        
        assertEquals(0, ticket.getRouteid());
        assertEquals("John Doe", ticket.getNameDTO());
        assertEquals("john@email.com", ticket.getEmailDTO());
        assertEquals("123456789", ticket.getPhoneDTO());
        assertEquals("Rua do ISEP", ticket.getAddressDTO());
        assertEquals("4200-072", ticket.getZipDTO());
        assertEquals("Porto", ticket.getCityDTO());
        assertEquals("Visa", ticket.getCardTypeDTO());
        assertEquals(123456789, ticket.getCardNumberDTO());
        assertEquals(12, ticket.getCardMonthDTO());
        assertEquals(2023, ticket.getCardYearDTO());
        assertEquals(123, ticket.getCardCvvDTO());
    }
}
