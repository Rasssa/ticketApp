package ticket.app.ticketApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PopUpsController {

    @RequestMapping("/congratulationsdelete")
    public String CongratulationsDeleteController() {
        return "popUps/popUpDelete";
    }


    @PostMapping(value = "/goBackDelete")
    public String CongratulationsDelete() { return "redirect:/tickets";
    }

    @RequestMapping("/congratulations")
    public String CongratulationsController() {
        return "popUps/popUp";
    }


    @PostMapping(value = "/goBack")
    public String Congratulations() { return "redirect:/";
    }

    @RequestMapping("/congratulationsstatusupdate")
    public String CongratulationsStatusUpdate() {
        return "popUps/popUpStatus";
    }

}
