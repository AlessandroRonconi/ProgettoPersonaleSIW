package it.uniroma3.siw.progetto_personale_siw.controller;

import it.uniroma3.siw.progetto_personale_siw.service.EsercizioService;
import it.uniroma3.siw.progetto_personale_siw.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateSchedaAllenamentoException;
import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.EsercizioScheda;
import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import it.uniroma3.siw.progetto_personale_siw.service.EsercizioSchedaService;
import it.uniroma3.siw.progetto_personale_siw.service.PersonalTrainerService;
import it.uniroma3.siw.progetto_personale_siw.service.SchedaAllenamentoService;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class SchedaController {

    private EsercizioService esercizioService;
    private UserService userService;
    private PersonalTrainerService personalTrainerService;
    private CredentialsService credentialsService;
    private SchedaAllenamentoService schedaAllenamentoService;
    private EsercizioSchedaService esercizioSchedaService;
    public SchedaController(EsercizioSchedaService esercizioSchedaService, CredentialsService credentialsService, SchedaAllenamentoService schedaAllenamentoService, UserService userService, PersonalTrainerService personalTrainerService, EsercizioService esercizioService){
        this.credentialsService = credentialsService;
        this.schedaAllenamentoService = schedaAllenamentoService;
        this.userService = userService;
        this.personalTrainerService = personalTrainerService;
        this.esercizioService = esercizioService;
        this.esercizioSchedaService = esercizioSchedaService;
    }

    @GetMapping("/utente/mie-schede")
    public String mostraScheda(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Credentials cred = this.credentialsService.getCredentials(userDetails.getUsername());
        User user = cred.getUser();
        List<SchedaAllenamento> scehdeAllenamento = this.schedaAllenamentoService.findByUser(user);
        model.addAttribute("schede", scehdeAllenamento);
        return "utente/mie-schede";
    }

    @GetMapping("/admin/schede/new")//aggiungi in security config
    public String nuovaScheda(Model model) {
        model.addAttribute("users", this.userService.findAll());
        model.addAttribute("scheda", new SchedaAllenamento());
        model.addAttribute("ptList", personalTrainerService.findAll());
        return "admin/schedaAllenamento/form";    
    }

    @PostMapping("/admin/schede/new")
    public String creaScheda( @Valid @ModelAttribute("scheda") SchedaAllenamento scheda,BindingResult bindingResult,@RequestParam Long userId,@RequestParam(required = false) Long ptId,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            model.addAttribute("ptList", personalTrainerService.findAll());
            return "admin/schedaAllenamento/form";
        }
        try {
            schedaAllenamentoService.creaScheda(scheda, userId, ptId);
        } catch (DuplicateSchedaAllenamentoException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("users", userService.findAll());
            model.addAttribute("ptList", personalTrainerService.findAll());
            return "admin/schedaAllenamento/form";
        }
        return "redirect:/admin/schede";
    }

    @GetMapping("/admin/schede")
    public String listaSchede(Model model) {
        model.addAttribute("schede", schedaAllenamentoService.findAll());
        return "admin/schedaAllenamento/list";//mi da tutte le schede di tutti gli utenti
    }

    @GetMapping("/admin/schede/{id}")
    public String showScheda(@PathVariable Long id, Model model) {
        model.addAttribute("scheda", schedaAllenamentoService.findById(id));
        return "admin/schedaAllenamento/show";//mostra la singola scheda
    }

    @GetMapping("/admin/schede/{id}/edit")
    public String editScheda(@PathVariable("id") Long id, Model model) {
        SchedaAllenamento scheda = schedaAllenamentoService.findById(id);
        model.addAttribute("scheda", scheda);
        model.addAttribute("ptList", this.personalTrainerService.findAll());
        model.addAttribute("userId", scheda.getUser().getId());
        return "admin/schedaAllenamento/editForm";
    }
    @PostMapping("/admin/schede/{id}/edit")
    public String modificaScheda(@PathVariable("id") Long id, @Valid @ModelAttribute("scheda") SchedaAllenamento schedaNuova, BindingResult bindingResult, @RequestParam Long userId,@RequestParam(required = false) Long ptId, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("ptList", this.personalTrainerService.findAll());
            model.addAttribute("userId", userId);
            return "admin/schedaAllenamento/editForm";
        }
        try {
            this.schedaAllenamentoService.updadeScheda(id, schedaNuova, ptId, userId);
        } catch (DuplicateSchedaAllenamentoException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("userId", userId);
            model.addAttribute("ptList", personalTrainerService.findAll());
            return "admin/schedaAllenamento/editForm";

        }
    return "redirect:/admin/schede/" + id;
    }

    @GetMapping("/admin/schede/{id}/esercizi/add")
    public String addEsercizio(@PathVariable("id") Long id, Model model) {
        model.addAttribute("scheda", this.schedaAllenamentoService.findById(id));
        model.addAttribute("esercizi", this.esercizioService.findAll());
        model.addAttribute("esercizioScheda", new EsercizioScheda());
        return "admin/schedaAllenamento/addEsercizi";
    }

    @PostMapping("/admin/schede/{id}/esercizi/add")
    public String addEsScheda(@PathVariable("id") Long id, @Valid @ModelAttribute("esercizioScheda") EsercizioScheda esercizioScheda, BindingResult bindingResult, @RequestParam Long esercizioId, Model model) {
        SchedaAllenamento scheda = schedaAllenamentoService.findById(id);
        if(bindingResult.hasErrors()){
            model.addAttribute("scheda", scheda);
            model.addAttribute("esercizi", esercizioService.findAll());
            model.addAttribute("esercizioScheda", esercizioScheda);
            return "admin/schedaAllenamento/addEsercizi";
        }
        this.esercizioSchedaService.save(id, esercizioId, esercizioScheda.getSerie(),esercizioScheda.getRipetizioni(),esercizioScheda.getRecuperoSec());
        return "redirect:/admin/schede/" + id;
    }
    
    
    
    
    
    
    
}
