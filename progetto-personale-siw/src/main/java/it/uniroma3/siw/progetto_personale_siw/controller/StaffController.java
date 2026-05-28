package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.progetto_personale_siw.service.IstruttoreService;
import it.uniroma3.siw.progetto_personale_siw.service.PersonalTrainerService;

@Controller
public class StaffController {

    private final PersonalTrainerService personalTrainerService;
    private final IstruttoreService istruttoreService;

    public StaffController(IstruttoreService istruttoreService, PersonalTrainerService personalTrainerService) {
        this.istruttoreService = istruttoreService;
        this.personalTrainerService = personalTrainerService;
    }

    @GetMapping("/staff")
    public String mostraPersonale(Model model) {
        model.addAttribute("istruttori", this.istruttoreService.findAll());
        model.addAttribute("personalTrainers", this.personalTrainerService.findAll());
        return "/staff/list";
    }

}
