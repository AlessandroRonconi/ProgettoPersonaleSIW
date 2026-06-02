package it.uniroma3.siw.progetto_personale_siw.controller;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateEsercizioException;
import it.uniroma3.siw.progetto_personale_siw.model.Esercizio;
import it.uniroma3.siw.progetto_personale_siw.service.EsercizioService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class EsercizioController {

    private EsercizioService esercizioService;

    EsercizioController(EsercizioService esercizioService) {
        this.esercizioService = esercizioService;
    }

    @GetMapping("/esercizi")//puo farlo l'admin
    public String mostraEsercizi(Model model) {
        model.addAttribute("esercizi", this.esercizioService.findAll());
        return "esercizi/show";
    }
    @GetMapping("/esercizi/new")//puo farlo l'admin
    public String creaEsercizio(Model model) {
        model.addAttribute("esercizio", new Esercizio());
        return "esercizi/form";
    }
    @PostMapping("/esercizi/new")//puo farlo l'admin
    public String creaEs(@Valid @ModelAttribute("esercizio") Esercizio esercizio, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "esercizi/form";
        }
        try {
            this.esercizioService.save(esercizio);
        } catch (DuplicateEsercizioException e) {
            model.addAttribute("errorMessage",e.getMessage());
            return "esercizi/form";
        }
        return "redirect:/esercizi";

    }
    
    
    
}
