package it.uniroma3.siw.progetto_personale_siw.exception;

public class CorsoOrarioConflictException extends RuntimeException {
    public CorsoOrarioConflictException(String giorno, String orario, String nomeCorso) {
        super("Il giorno '" + giorno + "' alle " + orario + " è già occupato dal corso '" + nomeCorso + "'");
    }
}
