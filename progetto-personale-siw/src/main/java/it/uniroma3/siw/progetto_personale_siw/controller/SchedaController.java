package it.uniroma3.siw.progetto_personale_siw.controller;

import it.uniroma3.siw.progetto_personale_siw.repository.SchedaAllenamentoRepository;
import it.uniroma3.siw.progetto_personale_siw.service.UserService;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateSchedaAllenamentoException;
import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import it.uniroma3.siw.progetto_personale_siw.service.PersonalTrainerService;
import it.uniroma3.siw.progetto_personale_siw.service.SchedaAllenamentoService;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
public class SchedaController {

    private UserService userService;
    private PersonalTrainerService personalTrainerService;
    private CredentialsService credentialsService;
    private SchedaAllenamentoService schedaAllenamentoService;
    public SchedaController(CredentialsService credentialsService, SchedaAllenamentoService schedaAllenamentoService, UserService userService, PersonalTrainerService personalTrainerService){
        this.credentialsService = credentialsService;
        this.schedaAllenamentoService = schedaAllenamentoService;
        this.userService = userService;
        this.personalTrainerService = personalTrainerService;
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
        model.addAttribute("users", userService.findAll());
        model.addAttribute("ptList", personalTrainerService.findAll());
        if (bindingResult.hasErrors()) {
            //model.addAttribute("users", userService.findAll());
            //model.addAttribute("ptList", personalTrainerService.findAll());
            return "admin/schedaAllenamento/form";
        }
        try {
            schedaAllenamentoService.creaScheda(scheda, userId, ptId);
        } catch (DuplicateSchedaAllenamentoException e) {
            model.addAttribute("error", e.getMessage());
            //model.addAttribute("users", userService.findAll());
            //model.addAttribute("ptList", personalTrainerService.findAll());
            return "admin/schedaAllenamento/form";
        }
        return "redirect:/admin/schede";
    }
    @GetMapping("/admin/schede")
    public String listaSchede(Model model) {
        model.addAttribute("schede", schedaAllenamentoService.findAll());
        return "admin/schedaAllenamento/list";
    }
    
    
    
    
}
