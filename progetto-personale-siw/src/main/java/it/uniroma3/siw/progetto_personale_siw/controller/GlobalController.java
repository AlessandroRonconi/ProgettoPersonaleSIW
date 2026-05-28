package it.uniroma3.siw.progetto_personale_siw.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController { // Serve per avere sempre disponibile l’utente loggato nelle pagine HTML
  // UserDetails è l’utente loggato visto da Spring Security
  @ModelAttribute("userDetails")
  public UserDetails getUser() {
    UserDetails user = null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken)) {
      user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    return user;
  }
}
