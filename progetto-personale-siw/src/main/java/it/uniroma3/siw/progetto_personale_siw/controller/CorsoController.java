package it.uniroma3.siw.progetto_personale_siw.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateCorsoException;
import it.uniroma3.siw.progetto_personale_siw.model.Commento;
import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.model.Istruttore;
import it.uniroma3.siw.progetto_personale_siw.service.CommentoService;
import it.uniroma3.siw.progetto_personale_siw.service.CorsoService;
import it.uniroma3.siw.progetto_personale_siw.service.IstruttoreService;
import jakarta.validation.Valid;

@Controller
public class CorsoController {

    private CorsoService corsoService;
    private CommentoService commentoService;
    private IstruttoreService istruttoreService;

    public CorsoController(CorsoService corsoService, CommentoService commentoService,
            IstruttoreService istruttoreService) {
        this.corsoService = corsoService;
        this.commentoService = commentoService;
        this.istruttoreService = istruttoreService;
    }

    @GetMapping("/corsi")
    public String mostraCorsi(Model model) {
        model.addAttribute("corsi", this.corsoService.findAll());
        return "corsi/list";
    }

    @GetMapping("/corsi/{id}/commenti")
    public String showRecensioni(@PathVariable Long id, Model model) {
        model.addAttribute("corso", this.corsoService.findById(id));
        model.addAttribute("commenti", this.commentoService.getCommentiByCorsoId(id));
        return "commenti/list";
    }

    @GetMapping("/corsi/calendario")
    public String showCalendario(Model model) {
        model.addAttribute("corsi", this.corsoService.findAll());
        return "corsi/calendario";
    }

    @GetMapping("/corsi/{id}/commenti/new")
    public String createComment(@PathVariable("id") Long id, Model model) {
        model.addAttribute("commento", new Commento());
        model.addAttribute("corso", this.corsoService.findById(id));
        return "commenti/form";
    }

    @PostMapping("/corsi/{id}/commenti/new")
    public String newComment(@PathVariable("id") Long id, @Valid @ModelAttribute("commento") Commento commento,
            BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("corso", this.corsoService.findById(id));
            return "commenti/form";
        }
        this.commentoService.creaCommento(id, userDetails.getUsername(), commento.getTesto());
        return "redirect:/corsi/" + id + "/commenti";
    }

    @GetMapping("/corsi/{corsoId}/commenti/{commentoId}/edit")
    public String editComment(@PathVariable("corsoId") Long corsoId, @PathVariable("commentoId") Long commentoId,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Commento commento1 = this.commentoService.findById(commentoId);
        if (commentoService.isNotOwner(commento1, userDetails.getUsername())) {
            return "redirect:/corsi/" + corsoId + "/commenti";
        }
        model.addAttribute("commento", commento1);
        model.addAttribute("corsoId", corsoId);
        return "commenti/editForm";
    }

    @PostMapping("/corsi/{corsoId}/commenti/{commentoId}/edit")
    public String editedComment(@PathVariable("corsoId") Long corsoId, @PathVariable("commentoId") Long commentoId,
            @Valid @ModelAttribute("commento") Commento commentoForm, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("corsoId", corsoId);
            model.addAttribute("commento", commentoForm);
            return "commenti/editForm";
        }
        Commento commentoOld = this.commentoService.findById(commentoId);
        try {
            commentoService.checkOwner(commentoOld, userDetails.getUsername());
        } catch (RuntimeException e) {
            return "redirect:/corsi/" + corsoId + "/commenti";
        }
        commentoOld.setTesto(commentoForm.getTesto());
        commentoOld.setDataOra(LocalDateTime.now());
        commentoService.save(commentoOld);
        return "redirect:/corsi/" + corsoId + "/commenti";
    }

    @GetMapping("/admin/corsi/new")
    public String showForm(Model model) {
        model.addAttribute("corso", new Corso());
        model.addAttribute("istruttori", istruttoreService.findAll());
        return "admin/corsi/form";
    }

    @PostMapping("/admin/corsi")
    public String salvaCorso(@Valid @ModelAttribute("corso") Corso corso,
            BindingResult bindingResult, Model model, @RequestParam(required = false) Long istruttoreId) {
        if (bindingResult.hasErrors()) {
            return "admin/corsi/form";
        }

        // Associa l'istruttore selezionato
        if (istruttoreId != null && istruttoreId > 0) {
            Optional<Istruttore> istruttore = istruttoreService.findById(istruttoreId);
            istruttore.ifPresent(corso::setIstruttore);
        }

        // Rimuovi eventuali orari vuoti
        corso.getWeekdayOrario().values().removeIf(String::isBlank);

        try {
            corsoService.save(corso);
            return "redirect:/corsi";
        } catch (DuplicateCorsoException e) {
            model.addAttribute("istruttori", istruttoreService.findAll());
            bindingResult.reject("corso.duplicate", "Esiste già un corso con questo nome");
            return "admin/corsi/form";
        }
    }

    @GetMapping("/admin/corsi/{id}/edit")    
    public String editForm(@PathVariable Long id, Model model) {
        Corso corso = this.corsoService.findById(id);
        if (corso.getWeekdayOrario() == null) {
            corso.setWeekdayOrario(new HashMap<>());
        }
        model.addAttribute("corso", corso);
        model.addAttribute("istruttori", istruttoreService.findAll());
        return "admin/corsi/form";
    }

    @PostMapping("/admin/corsi/{id}/edit")
    public String salvaEdit(@PathVariable Long id,
            @Valid @ModelAttribute("corso") Corso corsoForm,
            BindingResult bindingResult,
            Model model,
            @RequestParam(required = false) Long istruttoreId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("istruttori", istruttoreService.findAll());
            return "admin/corsi/form";
        }
        try {
            corsoService.update(corsoForm, id, istruttoreId); //passi il corso vecchio con id ed il corso modificato Form
        } catch (DuplicateCorsoException e) {
            model.addAttribute("istruttori", istruttoreService.findAll());
            model.addAttribute("errorMessage", e.getMessage());
            return "admin/corsi/form";
        }
        return "redirect:/corsi";
    }

    @PostMapping("/admin/corsi/{id}/delete")
    public String deleteCorso(@PathVariable Long id) {
        corsoService.deleteById(id);
        return "redirect:/corsi";
    }

}
