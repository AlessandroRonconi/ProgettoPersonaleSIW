package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateCredentialsException;
import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.service.CredentialsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController {

    private CredentialsService credentialsService;

    public AuthController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Prendo sessione se esiste
        if (session != null) {
            String loginError = (String) session.getAttribute("loginError"); // Leggo eventuale errore messo dal failure
                                                                             // handler
            if (loginError != null) {
                model.addAttribute("errorMessage", loginError); // Aggiungo messaggio al modello, quindi lo passo al
                                                                // template
                session.removeAttribute("loginError"); // Pulisco subito la sessione
            }
        }
        return "login"; // template per il login

    }

    @GetMapping("/register")
    public String MostraRegistrazione(Model model) {
        Credentials credentials = new Credentials();
        credentials.setUser(new User());
        model.addAttribute("credentials", credentials); // passo solo cred dato che è in onetoone con utente
        return "register";
    }

    @PostMapping("/register")
    public String Registrazione(@Valid @ModelAttribute Credentials credentials, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) { // errori di validazione
            return "register";
        }
        // sotto controllo errori di duplicazione
        try {
            credentialsService.save(credentials); // ho in credentials cascade, se salvo credentials salvo utente
        } catch (DuplicateCredentialsException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("credentials", credentials);
            return "register";
        }
        return "redirect:/login";
        // dopo il login, come faccio a sapere chi è loggato? GlobalController
    }

}
