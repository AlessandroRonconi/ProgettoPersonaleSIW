package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.progetto_personale_siw.service.TipoAbbonamentoService;

@Controller
public class TipoAbbonamentoController {

    private TipoAbbonamentoService tipoAbbonamentoService;

    public TipoAbbonamentoController(TipoAbbonamentoService tipoAbbonamentoService) {
        this.tipoAbbonamentoService = tipoAbbonamentoService;
    }

    @GetMapping("/tipi_abbonamenti")
    public String mostraTipiAbbonamenti(Model model) {
        model.addAttribute("tipi_abbonamenti", this.tipoAbbonamentoService.findAll());
        return "tipi_abbonamenti/list";
    }
}
