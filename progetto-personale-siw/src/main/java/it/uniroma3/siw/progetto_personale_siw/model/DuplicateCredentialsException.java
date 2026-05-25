package it.uniroma3.siw.progetto_personale_siw.model;

public class DuplicateCredentialsException extends RuntimeException {
    
    public DuplicateCredentialsException(String message) {
        super(message);
    }
}