package it.uniroma3.siw.progetto_personale_siw.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.Prenotazione;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CorsoService;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import it.uniroma3.siw.progetto_personale_siw.service.PrenotazioneService;

import org.springframework.ui.Model;


@Controller
public class UserController {

    private CredentialsService credentialsService;
    private CorsoService corsoService;
    private PrenotazioneService prenotazioneService;
    public UserController(CredentialsService credentialsService, CorsoService corsoService, PrenotazioneService prenotazioneService){
        this.credentialsService = credentialsService;
        this.prenotazioneService = prenotazioneService;
        this.corsoService = corsoService;
    }

    @GetMapping("/utente/profilo")
    public String getPaginaUser(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Credentials cred = this.credentialsService.getCredentials(userDetails.getUsername());
        User user = cred.getUser();
        model.addAttribute("utente", user);
        return "utente/profilo";
    }
    @GetMapping("/utente/prenota-corsi")
    public String paginaPrenotaCorsi(Model model) {
        model.addAttribute("corsi", corsoService.findAll());
        return "utente/prenota-corsi";
    }
    @PostMapping("/utente/prenota/{corsoId}")
    public String prenota(@PathVariable Long corsoId, @AuthenticationPrincipal UserDetails userDetails) {
        Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());
        User user = credentials.getUser();
        
        prenotazioneService.prenota(user,corsoId);
        return "redirect:/utente/prenota-corsi";
    }
    @GetMapping("/utente/prenotazioni")
    public String getPrenotazioniUtente(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        User user = credentials.getUser();
        List<Prenotazione> prenotazioni = prenotazioneService.findByUser(user);
        model.addAttribute("prenotazioni", prenotazioni);
        model.addAttribute("utente", user);
        return "utente/prenotazioni";
    }   
    
}
