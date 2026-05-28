package it.uniroma3.siw.progetto_personale_siw.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.Prenotazione;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CorsoService;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import it.uniroma3.siw.progetto_personale_siw.service.PrenotazioneService;

@Controller
public class UserController {

    private CredentialsService credentialsService;
    private CorsoService corsoService;
    private PrenotazioneService prenotazioneService;

    public UserController(CredentialsService credentialsService, CorsoService corsoService,
            PrenotazioneService prenotazioneService) {
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
    public String paginaPrenotaCorsi(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // 1. Aggiungiamo tutti i corsi disponibili
        model.addAttribute("corsi", corsoService.findAll());

        // 2. Se l'utente è loggato, recuperiamo i suoi corsi già prenotati
        if (userDetails != null) {
            Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
            User user = credentials.getUser();

            // Chiediamo al service le prenotazioni di questo specifico utente
            List<Prenotazione> prenotazioniUtente = prenotazioneService.findByUser(user);

            // Trasformiamo la lista di prenotazioni in un Set di soli ID dei corsi
            Set<Long> corsiPrenotatiIds = prenotazioniUtente.stream()
                    .map(p -> p.getCorso().getId())
                    .collect(Collectors.toSet());

            // Passiamo il Set alla pagina Thymeleaf
            model.addAttribute("corsiPrenotatiIds", corsiPrenotatiIds);
        }

        return "utente/prenota-corsi";
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