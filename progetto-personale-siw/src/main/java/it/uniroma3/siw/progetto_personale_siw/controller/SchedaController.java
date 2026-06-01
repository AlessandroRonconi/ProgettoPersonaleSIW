package it.uniroma3.siw.progetto_personale_siw.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import it.uniroma3.siw.progetto_personale_siw.service.SchedaAllenamentoService;


@Controller
public class SchedaController {

    private CredentialsService credentialsService;
    private SchedaAllenamentoService schedaAllenamentoService;
    public SchedaController(CredentialsService credentialsService, SchedaAllenamentoService schedaAllenamentoService){
        this.credentialsService = credentialsService;
        this.schedaAllenamentoService = schedaAllenamentoService;
    }

    @GetMapping("/utente/mie-schede")
    public String mostraScheda(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Credentials cred = this.credentialsService.getCredentials(userDetails.getUsername());
        User user = cred.getUser();
        List<SchedaAllenamento> scehdeAllenamento = this.schedaAllenamentoService.findByUser(user);
        model.addAttribute("schede", scehdeAllenamento);
        return "utente/mie-schede";
    }
    
}
