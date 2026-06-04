package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import it.uniroma3.siw.progetto_personale_siw.service.PrenotazioneService;

@Controller
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;
    private final CredentialsService credentialsService;

    public PrenotazioneController(PrenotazioneService prenotazioneService, CredentialsService credentialsService) {
        this.prenotazioneService = prenotazioneService;
        this.credentialsService = credentialsService;
    }

    @PostMapping("/utente/prenota/{id}")
    public String prenotazione(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Credentials cred = this.credentialsService.getCredentials(userDetails.getUsername());
        User user = cred.getUser();
        prenotazioneService.prenota(user.getId(), id);

        return "redirect:/utente/prenotazioni";
    }

}
