package it.uniroma3.siw.progetto_personale_siw.controller;
import org.springframework.ui.Model;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import it.uniroma3.siw.progetto_personale_siw.service.CorsoService;


@Controller
public class CorsoController {

    private CorsoService corsoService;
    public CorsoController(CorsoService corsoService){
        this.corsoService = corsoService;
    }

    @GetMapping("/corsi")
    public String mostraCorsi(Model model) {
        model.addAttribute("corsi", this.corsoService.findAll());
        return "corsi/list";
    }
    
}
