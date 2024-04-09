package tqs.example.service;


import tqs.example.dto.TicketDTO;
import tqs.example.entity.Ticket;

public interface TicketService {

    Ticket createTicket(TicketDTO ticket);
    Ticket getTicketById(String id);

    
}