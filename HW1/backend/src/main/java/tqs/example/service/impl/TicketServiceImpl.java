package tqs.example.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import tqs.example.dto.TicketDTO;
import tqs.example.entity.Person;
import tqs.example.entity.Route;
import tqs.example.entity.Ticket;
import tqs.example.repository.PersonRepository;
import tqs.example.repository.RouteRepository;
import tqs.example.repository.TicketRespository;
import tqs.example.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRespository ticketRespository;
    private final PersonRepository personRepository;
    private final RouteRepository routeRepository;

    public String generateTicketUID() {
        return UUID.randomUUID().toString();
    }

    public TicketServiceImpl(TicketRespository ticketRespository, PersonRepository personRepository,
            RouteRepository routeRepository) {
        this.ticketRespository = ticketRespository;
        this.personRepository = personRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public Ticket createTicket(TicketDTO ticketDTO) {

        Route route = routeRepository.findByRouteId(ticketDTO.getRouteid());
        if (route.getOcupation() == 0) {

            return null;

        } else {
            route.setOcupation(route.getOcupation() - 1);
            routeRepository.save(route);

            

            Person person = new Person();
            person.setName(ticketDTO.getNameDTO());
            person.setEmail(ticketDTO.getEmailDTO());
            person.setPhone(ticketDTO.getPhoneDTO());
            person.setAddress(ticketDTO.getAddressDTO());
            person.setZip(ticketDTO.getZipDTO());
            person.setCity(ticketDTO.getCityDTO());
            personRepository.save(person);

            Ticket ticket = new Ticket();
            ticket.setId(generateTicketUID());
            ticket.setPersonid(person.getId());
            ticket.setPerson(person);
            ticket.setRouteid(ticketDTO.getRouteid());
            ticket.setRoute(routeRepository.findByRouteId(ticketDTO.getRouteid()));
            ticket.setCardType(ticketDTO.getCardTypeDTO());
            ticket.setCardNumber(ticketDTO.getCardNumberDTO());
            ticket.setCardMonth(ticketDTO.getCardMonthDTO());
            ticket.setCardYear(ticketDTO.getCardYearDTO());
            ticket.setCardCvv(ticketDTO.getCardCvvDTO());
            ticketRespository.save(ticket);

            return ticket;
        }

    }

    @Override
    public Ticket getTicketById(String id) {

        return ticketRespository.findByTicketId(id);

    }

}