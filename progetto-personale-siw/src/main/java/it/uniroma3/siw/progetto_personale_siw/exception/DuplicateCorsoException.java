package it.uniroma3.siw.progetto_personale_siw.exception;

public class DuplicateCorsoException extends RuntimeException {

    public DuplicateCorsoException(String nome) {
        super("Il corso '" + nome + "' è già presente nel sistema");
    }
}
