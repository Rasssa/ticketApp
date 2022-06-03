package ticket.app.ticketApp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ticket.app.ticketApp.enums.Category;
import ticket.app.ticketApp.enums.Status;
import ticket.app.ticketApp.model.Ticket;
import ticket.app.ticketApp.services.UserService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class StatisticsController {

    @Autowired
    ticket.app.ticketApp.services.TicketService ticketService;

    @Autowired
    UserService userService;

    @RequestMapping("/statistics")
    public String StatisticsController(Model model) {
        model.addAttribute("tickets",ticketService.findAll().size());
        model.addAttribute("hardwareTickets", filterTicketsByCategory(Category.HARDWARE.getName()).size());
        model.addAttribute("applicationTickets", filterTicketsByCategory(Category.APPLICATION.getName()).size());
        model.addAttribute("systemTickets", filterTicketsByCategory(Category.SYSTEM.getName()).size());
        model.addAttribute("other", filterTicketsByCategory(Category.OTHER.getName()).size());
        model.addAttribute("statusNew", filterTicketsByStatus(Status.NEW.getName()).size());
        model.addAttribute("statusInProgress", filterTicketsByStatus(Status.IN_PROGRESS.getName()).size());
        model.addAttribute("statusFinished", filterTicketsByStatus(Status.FINISHED.getName()).size());
        model.addAttribute("statusCanceled", filterTicketsByStatus(Status.CANCELED.getName()).size());
        model.addAttribute("statusStopped", filterTicketsByStatus(Status.STOPPED.getName()).size());
        model.addAttribute("users", userService.findAll().size());
        return "statistics/statistics";
    }


    @PostMapping(value = "/goTickets")
    public String Statistics() {
        return "redirect:/tickets";
    }

    private List<Ticket> filterTicketsByCategory(String category) {
        return ticketService.findAll().stream().filter(x -> x.getCategory().equals(category)).collect(Collectors.toList());
    }
    private List<Ticket> filterTicketsByStatus(String status) {
        return ticketService.findAll().stream().filter(x -> x.getStatus().equals(status)).collect(Collectors.toList());
    }

}


