package ticket.app.ticketApp.services;

import org.springframework.stereotype.Service;
import ticket.app.ticketApp.model.Ticket;

@Service
public class NewTicketServiceLMPL implements NewTicketService{

    private TicketService ticketService;

    public NewTicketServiceLMPL(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    public void addNewTicket(String email, String title, String category, String description) {
        Ticket ticket = new Ticket();
        ticket.setEmail(email);
        ticket.setTitle(title);
        ticket.setCategory(category);
        ticket.setDescription(description);
        ticketService.save(ticket);
    }

    @Override
    public void deleteTicket(Long ticket_id) {
    }

    @Override
    public void updateStatus(Long ticket_id, String status) {
    }
}

