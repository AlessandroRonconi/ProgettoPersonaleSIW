package it.uniroma3.siw.progetto_personale_siw.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.progetto_personale_siw.service.CommentoService;
import it.uniroma3.siw.progetto_personale_siw.service.CorsoService;




@Controller
public class CorsoController {

    private CorsoService corsoService;
    private CommentoService commentoService;
    public CorsoController(CorsoService corsoService, CommentoService commentoService){
        this.corsoService = corsoService;
        this.commentoService = commentoService;
    }

    @GetMapping("/corsi")
    public String mostraCorsi(Model model) {
        model.addAttribute("corsi", this.corsoService.findAll());
        return "corsi/list";
    }

    @GetMapping("/corsi/{id}/commenti")
    public String showRecensioni(@PathVariable Long id, Model model) {
        model.addAttribute("corsi", this.corsoService.findById(id));
        model.addAttribute("commenti",this.commentoService.getCommentiByCorsoId(id));
        return "commenti/list";
    }

    @GetMapping("/corsi/calendario")
    public String showCalendario(Model model) {
        model.addAttribute("corsi", this.corsoService.findAll());
        return "corsi/calendario";
    }
    

    /*@GetMapping("/corsi/{id}/commenti/new")
    //addare un commento ad una partita specifica(quindi serve id della partita)
    public String createComment(@PathVariable("id") Long id, Model model) {
    
        return "commenti/form";
    }

    @PostMapping("/corso/{id}/commenti/new")
    public String newComment(@PathVariable("id") Long id, @Valid @ModelAttribute("commento") Commento commento, BindingResult bindingResult,@AuthenticationPrincipal UserDetails userDetails, Model model) {   
        
    }
    
    @GetMapping("/partite/{partitaId}/commenti/{commentoId}/edit")
    //devo modificare un commento in una partita
    public String editComment(@PathVariable("partitaId") Long partitaId ,@PathVariable("commentoId") Long commentoId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        
        
        return "commenti/editForm";
    }
    
    @PostMapping("/partite/{partitaId}/commenti/{commentoId}/edit")
    public String editedComment(@PathVariable("partitaId") Long partitaId, @PathVariable("commentoId") Long commentoId, @Valid @ModelAttribute("commento") Commento commentoForm, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model) {    
        
    }*/

    
}
