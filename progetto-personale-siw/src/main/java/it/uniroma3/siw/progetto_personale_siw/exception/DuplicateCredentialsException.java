package it.uniroma3.siw.progetto_personale_siw.exception;

public class DuplicateCredentialsException extends RuntimeException {
    
    public DuplicateCredentialsException(String message) {
        super(message);
    }
}