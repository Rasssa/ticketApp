package ticket.app.ticketApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ticket.app.ticketApp.model.Ticket;
import ticket.app.ticketApp.services.NewTicketService;
import ticket.app.ticketApp.services.TicketService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TicketPageController {
    @Autowired
    TicketService ticketService;

    @Autowired
    private NewTicketService newTicketService;

    @RequestMapping("/tickets")
    public String ticketsController(Model model) {

        model.addAttribute("allTickets", ticketService.findAll());
        model.addAttribute("hardwareTickets", filterTickets("Problem with hardware"));
        model.addAttribute("applicationTickets", filterTickets("Problem with application"));
        model.addAttribute("systemTickets", filterTickets("Problem with system"));
        model.addAttribute("Other", filterTickets("Other"));
        return "ticketPage/ticketPage";
    }

    @PostMapping(value = "/delete")
    public String deleteTicket(@RequestParam("TicketName") Long ticket_id) {
        ticketService.delete(ticketService.findTicketById(ticket_id));
        System.out.println("Ticket with ID: " + ticket_id + " was successfully deleted");
        return "redirect:/congratulationsdelete";
    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public String changeStatus(@RequestParam("TicketName") Long ticket_id,
                               @RequestParam("status") String status) {
            Ticket ticket = ticketService.findTicketById(ticket_id);
            ticket.setStatus(status);
            ticketService.save(ticket);
        return "redirect:/congratulationsstatusupdate";
    }

    private List<Ticket> filterTickets(String category) {
        return ticketService.findAll().stream().filter(x -> x.getCategory().equals(category)).collect(Collectors.toList());
    }

    /*public boolean statusLogic(String status, Long ticket_id) {
        String actualStatus = ticketService.findById(ticket_id).get().getStatus();
        switch (status) {
            case "In progress":
            case "Canceled":
                return !actualStatus.equals(Status.FINISHED.getName());
            case "Stopped":
            case "Finished":
                return actualStatus.equals(Status.IN_PROGRESS.getName());
            default:
                return false;
        }
    }*/

}