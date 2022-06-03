package IT.Data;

import ticket.app.ticketApp.model.Ticket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TicketData {

    public static Ticket createTicket(String emial, String title, String category, String desc){
        Ticket ticket = new Ticket();
        ticket.setEmail(emial);
        ticket.setTitle(title);
        ticket.setCategory(category);
        ticket.setDescription(desc);
        return ticket;
    }

    public static void ticketComparator(Ticket ticket, Ticket secondTicket){

        assertThat(ticket.getEmail(), is(equalTo(secondTicket.getEmail())));
        assertThat(ticket.getTitle(), is(equalTo(secondTicket.getTitle())));
        assertThat(ticket.getCategory(), is(equalTo(secondTicket.getCategory())));
        assertThat(ticket.getDescription(), is(equalTo(secondTicket.getDescription())));

    }
}
