package it.uniroma3.siw.progetto_personale_siw.config;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.uniroma3.siw.progetto_personale_siw.model.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Gestisce "risorsa non trovata" (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ResourceNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404";
    }

    // 3. Gestisce TUTTI gli altri errori imprevisti (500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericError(Exception e, Model model) {
        model.addAttribute("errorMessage", "Si è verificato un errore imprevisto");
        return "error/500";
    }
}