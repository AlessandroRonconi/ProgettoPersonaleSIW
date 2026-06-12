package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.progetto_personale_siw.service.PersonalTrainerService;

import org.springframework.ui.Model;


@Controller
public class PersonalTrainerController {

    private PersonalTrainerService personalTrainerService;
    public PersonalTrainerController(PersonalTrainerService personalTrainerService){
        this.personalTrainerService = personalTrainerService;
    }
    @GetMapping("/admin/elenco")
    public String PtEUser(Model model) {
        model.addAttribute("pts", this.personalTrainerService.findAll());
        return "pt/elenco";
    }
    
}
