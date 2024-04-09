package tqs.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tqs.example.entity.Ticket;


@Repository
public interface TicketRespository extends JpaRepository<Ticket, String>{
    Ticket findByTicketId(String ticketId);
    List<Ticket> findByPersonId(int personId);
}
