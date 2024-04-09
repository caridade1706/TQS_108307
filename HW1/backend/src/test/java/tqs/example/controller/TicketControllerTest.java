package tqs.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;

import com.fasterxml.jackson.databind.ObjectMapper;

import tqs.example.dto.TicketDTO;
import tqs.example.entity.Ticket;
import tqs.example.service.impl.RouteServiceImpl;
import tqs.example.service.impl.TicketServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc 
class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TicketServiceImpl ticketService;

    @MockBean
    private RouteServiceImpl routeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenReserveTicket_thenReturnsTicket() throws Exception {
        TicketDTO ticketDTO = new TicketDTO(0, "John Doe", "john@email.com",
                "123456789", "Rua do ISEP", "4200-072", "Porto",
                "Visa", 123456789, 12, 2023, 123);
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());

        when(routeService.isfull(any())).thenReturn(false);
        when(ticketService.createTicket(any(TicketDTO.class))).thenReturn(ticket);

        String ticketJson = new ObjectMapper().writeValueAsString(ticketDTO);

        mvc.perform(post("/api/tickets/createreservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is(ticket.getId())));
    
        verify(ticketService, times(1)).createTicket(any(TicketDTO.class));
    }

    @Test
    void whenReserveTicketAndRouteIsFull_thenReturnsBadRequest() throws Exception {
      
        TicketDTO ticketDTO = new TicketDTO(1, "Jane Doe", "jane@email.com",
                "987654321", "Avenue", "54321", "New York",
                "Visa", 123456789, 12, 2023, 123);

        when(routeService.isfull(any())).thenReturn(true);

        String ticketJson = new ObjectMapper().writeValueAsString(ticketDTO);

        mvc.perform(post("/api/tickets/createreservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Bus is full")));

        verify(ticketService, never()).createTicket(any(TicketDTO.class));
    }

    @Test
    void whenGetTicketById_thenReturnsTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());

        when(ticketService.getTicketById(ticket.getId())).thenReturn(ticket);

        mvc.perform(get("/api/tickets/reservation/{id}", ticket.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ticket.getId())));

        verify(ticketService, times(1)).getTicketById(ticket.getId());
    }

    @Test
    void whenReserveTicket_thenThereIsNoTicket() throws Exception {
        TicketDTO ticketDTO = new TicketDTO(1, "Jane Doe", "jane@email.com",
                "987654321", "Avenue", "54321", "New York",
                "Visa", 123456789, 12, 2023, 123);
    
        when(ticketService.createTicket(any(TicketDTO.class))).thenReturn(null);
    
        String ticketJson = new ObjectMapper().writeValueAsString(ticketDTO);
    
        mvc.perform(post("/api/tickets/createreservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ticketJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Ticket not created")));
    
        verify(ticketService, times(1)).createTicket(any(TicketDTO.class));
    }
    
}
