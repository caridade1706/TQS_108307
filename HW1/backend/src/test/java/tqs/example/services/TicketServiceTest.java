package tqs.example.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import tqs.example.dto.TicketDTO;
import tqs.example.entity.Bus;
import tqs.example.entity.Person;
import tqs.example.entity.Route;
import tqs.example.entity.Ticket;
import tqs.example.repository.PersonRepository;
import tqs.example.repository.RouteRepository;
import tqs.example.repository.TicketRespository;
import tqs.example.service.impl.TicketServiceImpl;


@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock(lenient = true)
    private TicketRespository ticketRepository;

    @Mock(lenient = true)
    private PersonRepository personRepository;

    @Mock(lenient = true)
    private RouteRepository routeRepository;

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    private Ticket ticket;
    private Route route;
    private Person person;
    private TicketDTO ticketDTO;
    


    @BeforeEach
    void setUp() {
        Bus bus = new Bus("AA0000", 50, "TST", "Mercedes", 2021);
        route = new Route(0,"Porto", "Lisboa", "2021-05-01", "12:00", "14:00", 50, 100, 50,"AA0000", bus);
        person = new Person(0, "John Doe", "john@email.com", "123456789", "Street", "12345", "Seattle");
        ticket = new Ticket("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", "Visa", 123456789, 12, 2023, 123,0,0, person, route);
        ticketDTO = new TicketDTO();
        

        Mockito.when(ticketRepository.findByTicketId(ticket.getId())).thenReturn(ticket);
        Mockito.when(personRepository.save(person)).thenReturn(person);
        Mockito.when(ticketRepository.save(ticket)).thenReturn(ticket);
        Mockito.when(routeRepository.findByRouteId(0)).thenReturn(route);
    }

    @Test
    void whenGetTicketById_thenTicketShouldBeFound() {
        Ticket found = ticketServiceImpl.getTicketById(ticket.getId());
        assert(found != null);
        assertEquals("d20ab270-7c81-4087-a7a6-259b0aOcc3ea", found.getId());
        assertEquals("Visa", found.getCardType(), "Card type does not match");
        assertEquals(123456789, found.getCardNumber(), "Card number does not match");
        assertEquals(12, found.getCardMonth(), "Card month does not match");
        assertEquals(2023, found.getCardYear(), "Card year does not match");
        assertEquals(123, found.getCardCvv(), "Card CVV does not match");
        assertEquals(ticket.getPerson(), found.getPerson(), "Person does not match");
        assertEquals(0, found.getPersonid(), "Person ID does not match");
        assertEquals(ticket.getRoute(), found.getRoute(), "Route does not match");
        assertEquals(0, found.getRouteid(), "Route ID does not match");
    }

    @Test
    void uuidTest() {
        assertEquals(36,ticketServiceImpl.generateTicketUID().length());
    }

    @Test
    void whenCreateTicketAndRouteIsFull_thenNoTicketShouldBeCreated() {

        route.setOcupation(0); 
        Ticket createdTicket = ticketServiceImpl.createTicket(ticketDTO);
       
        assertThat(createdTicket).isNull();

        verify(routeRepository).findByRouteId(ticketDTO.getRouteid());
        verify(personRepository, Mockito.never()).save(person);
        verify(ticketRepository, Mockito.never()).save(ticket);
    }

    @Test
    void whenCreateTicket_thenTicketShouldBeCreated() {
        int initialOccupation = route.getOcupation();
        route.setOcupation(initialOccupation - 1);
        Ticket createdTicket = ticketServiceImpl.createTicket(ticketDTO);
        
        assertThat(createdTicket).isNotNull();
        assertThat(createdTicket.getPerson().getId()).isEqualTo(person.getId());
        assertThat(createdTicket.getRoute()).isEqualTo(route);

        verify(ticketRepository, times(1)).save(createdTicket);

    }

    
}
