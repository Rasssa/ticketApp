package IT.Scenarios;

import IT.Data.RestUtils;
import IT.Data.TicketData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ticket.app.ticketApp.TicketAppApplication;
import ticket.app.ticketApp.model.Ticket;
import ticket.app.ticketApp.services.TicketService;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TicketAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class MainPageIT {
    String email;
    String title;
    String desc;

    @Autowired
    TicketService ticketService;

    @BeforeAll
    public static void initializeRestUtils() {
        RestUtils.buildRequest(8080);
    }

    @BeforeEach
    public void generateRandomData() {
        this.email = RandomStringUtils.random(5, true, true) + "@gmail.com";
        this.title = RandomStringUtils.random(5, true, true);
        this.desc = RandomStringUtils.random(5, true, true);
    }

    @Test
    public void addNewTicketCorrectly() {
        //given
        Ticket manualTicket = TicketData.createTicket(email, title, "Problem with hardware", desc);

        //when
        RestUtils.addTicketViaEndpoint(manualTicket);

        Ticket ticketFromDb = ticketService.findAll().stream()
                .filter(x -> x.getTitle().equals(title)).collect(Collectors.toList()).get(0);


        //then
        TicketData.ticketComparator(manualTicket, ticketFromDb);
    }

    @Test
    public void addTicketWithWrongEmail() {
        //given
        List<Ticket> firstTicketList = ticketService.findAll();
        email = "wrongTestEmail";
        Ticket manualTicket = TicketData.createTicket(email, title, "Problem with hardware", desc);

        //when
        RestUtils.addTicketViaEndpoint(manualTicket);

        //then
        assertThat(firstTicketList.size(), is(equalTo(ticketService.findAll().size())));
    }

    @Test
    public void addTicketWithWrongTitle() {
        //given
        List<Ticket> firstTicketList = ticketService.findAll();
        title = "";
        Ticket manualTicket = TicketData.createTicket(email, title, "Problem with hardware", desc);

        //when
        RestUtils.addTicketViaEndpoint(manualTicket);

        //then
        assertThat(firstTicketList.size(), is(equalTo(ticketService.findAll().size())));
    }

    @Test
    public void addTicketWithWrongDesc() {
        //given
        List<Ticket> firstTicketList = ticketService.findAll();
        desc = "";
        Ticket manualTicket = TicketData.createTicket(email, title, "Problem with hardware", desc);

        //when
        RestUtils.addTicketViaEndpoint(manualTicket);

        //then
        assertThat(firstTicketList.size(), is(equalTo(ticketService.findAll().size())));
    }

    @Test
    public void addTicketWithWrongCategory() {
        //given
        List<Ticket> firstTicketList = ticketService.findAll();
        Ticket manualTicket = TicketData.createTicket(email, title, "nonExisting", desc);

        //when
        RestUtils.addTicketViaEndpoint(manualTicket);

        //then
        assertThat(firstTicketList.size(), is(equalTo(ticketService.findAll().size())));
    }

}
