package ticket.app.ticketApp.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticket.app.ticketApp.model.Ticket;
import ticket.app.ticketApp.model.User;

@Repository
public interface UserService extends JpaRepository<User, Long> {
}