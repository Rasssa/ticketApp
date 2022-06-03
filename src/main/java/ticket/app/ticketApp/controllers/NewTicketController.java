package ticket.app.ticketApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ticket.app.ticketApp.model.Ticket;
import ticket.app.ticketApp.services.TicketService;

import java.util.List;
import java.util.stream.Collectors;

@Controller

public class NewTicketController {

   TicketService ticketService;

    public NewTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping("/")
    public String newTicketController(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("filtredTickets", filterTicketsByLogin("String email"));
        return "mainPage/newTicket";
    }


    @PostMapping(value = "/addTicket")
    public String addNewTicket(@ModelAttribute Ticket ticket) throws Exception {
            ticket.setStatus("New");
            ticketService.save(ticket);
            /*JavaMailUtil.sendMail(ticket.getEmail());*/
                return "redirect:/congratulations";
            }


    private List<Ticket> filterTicketsByLogin(String email) {
        return ticketService.findAll().stream().filter(x -> x.getEmail().equals(email)).collect(Collectors.toList());
    }
}
