package ticket.app.ticketApp.services;

import org.springframework.stereotype.Repository;

@Repository
public interface NewTicketService{

    void deleteTicket(Long ticket_id);

    void updateStatus(Long ticket_id, String status);

}
