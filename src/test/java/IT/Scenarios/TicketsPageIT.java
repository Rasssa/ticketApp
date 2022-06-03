package IT.Scenarios;

import IT.Data.RestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ticket.app.ticketApp.TicketAppApplication;
import ticket.app.ticketApp.services.NewTicketService;
import ticket.app.ticketApp.services.TicketService;

import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TicketAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TicketsPageIT {
    @Autowired
    TicketService ticketService;

    @Autowired
    NewTicketService newTicketService;

    @BeforeAll
    public static void initializeRestUtils() {
        RestUtils.buildRequest(8080);
    }

    @Test
    public void deleteTicketViaEndpoint() {
        //given
        /*newTicketService.addNewTicket(1l,
                "testemail@gmail.com", "testTitle", "Problem with hardware", "longTestDesc");
*/
        long ticketId = ticketService.findAll().stream()
                .filter(x -> x.getDescription().equals("longTestDesc"))
                .collect(Collectors.toList())
                .get(0).getId();

        //when
        RestUtils.deleteTicket(ticketId);

        //then
        assertNull(ticketService.findById(ticketId).orElse(null));
    }

    @Test
    public void deleteNonExistingTicket() {
        //given
        /*newTicketService.addNewTicket(2l,
                "testemail@gmail.com", "testTitle", "Problem with hardware", "longTestDesc");
        newTicketService.addNewTicket(3l,
                "testemail@gmail.com", "testTitle", "Problem with hardware", "longTestDesc");
        newTicketService.addNewTicket(4l,
                "testemail@gmail.com", "testTitle", "Problem with hardware", "longTestDesc");*/
        int beforeDeleteSize = ticketService.findAll().size();

        //when
        RestUtils.deleteTicket(999L);

        int afterDeleteSize = ticketService.findAll().size();

        //then
        assertEquals(beforeDeleteSize, afterDeleteSize);
    }

}
