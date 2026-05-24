package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import it.uniroma3.siw.progetto_personale_siw.service.AbbonamentoService;

@Controller
public class AbbonamentoController {

    private AbbonamentoService abbonamentoService;
    public AbbonamentoController(AbbonamentoService abbonamentoService){
        this.abbonamentoService = abbonamentoService;
    }

    @GetMapping("/abbonamenti")
    public String mostraAbbonamenti(Model model) {
        model.addAttribute("abbonamenti", this.abbonamentoService.findAll());
        return "abbonamenti/list";
    }
}
