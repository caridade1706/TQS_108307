package tqs.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tqs.example.dto.TicketDTO;
import tqs.example.entity.Ticket;
import tqs.example.service.RouteService;
import tqs.example.service.TicketService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final RouteService routeService;

    private static Logger logger = LogManager.getLogger(TicketController.class);


    @GetMapping("/reservation/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") String id) {
        Ticket ticket = ticketService.getTicketById(id);
        logger.info("Getting ticket by id");
        logger.info("Ticket: {}",ticket);
        return ResponseEntity.ok(ticket);

    }

    @PostMapping("/createreservation")
    public ResponseEntity<Object> reserveTicket(@RequestBody TicketDTO ticketDTO){
        
        logger.info("Creating ticket");
        
        if(routeService.isfull(routeService.getRouteById(ticketDTO.getRouteid()))){
            logger.info("Route is full");
            return ResponseEntity.badRequest().body("Bus is full");
        }

        Ticket ticket = ticketService.createTicket(ticketDTO);

        if (ticket == null){
            logger.info("Ticket not created");
            return ResponseEntity.badRequest().body("Ticket not created");
        }
        else{
            Map<String, Object> response = new HashMap<>();
            response.put("ticket", ticket.getId());
            logger.info("Ticket created");
            return ResponseEntity.ok(response);
        }    }


    
}
