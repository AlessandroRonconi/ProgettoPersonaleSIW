package it.uniroma3.siw.progetto_personale_siw.model;

public class DuplicateEsercizioException extends RuntimeException{
    public DuplicateEsercizioException() {
        super("Esercizio gia esistente");
    }
}
