package ticket.app.ticketApp.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ticket.app.ticketApp.model.Ticket;

import java.util.List;

@Repository
public interface TicketService extends CrudRepository<Ticket, Long> {
    List<Ticket> findAll();
    Ticket findTicketById (Long ticket_id);
}