package it.uniroma3.siw.progetto_personale_siw.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.Commento;
import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.CommentoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.CorsoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.CredentialsRepository;

@Service
@Transactional
public class CommentoService {

    private CorsoRepository corsoRepository;
    private CredentialsRepository credentialsRepository;
    private CommentoRepository commentoRepository;
    public CommentoService(CommentoRepository commentoRepository, CorsoRepository corsoRepository, CredentialsRepository credentialsRepository){
        this.commentoRepository = commentoRepository;
        this.corsoRepository = corsoRepository;
        this.credentialsRepository = credentialsRepository;
    }

    public Object getCommentiByCorsoId(Long id) {
        return this.commentoRepository.findByCorsoId(id);
    }

    public Commento creaCommento(Long id, String username, String testo) {
        Corso corso = this.corsoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Corso non trovato"));
        Credentials credentials = this.credentialsRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Credenziali non trovate"));
        User user = credentials.getUser();
        Commento commento  = new Commento();
        commento.setAutore(username);
        commento.setTesto(testo);
        commento.setCorso(corso);
        commento.setDataOra(LocalDateTime.now());
        commento.setUser(user);
        return commentoRepository.save(commento);
    }
    @Transactional(readOnly = true)
    public Commento findById(Long commentoId) {
        return this.commentoRepository.findById(commentoId).orElseThrow(() -> new ResourceNotFoundException("Commento non trovato"));
    }

    @Transactional(readOnly = true)
    public boolean isNotOwner(Commento commentoOld, String username) {
        return !commentoOld.getAutore().equals(username); //ritorna true se sono uguali
    }

    @Transactional(readOnly = true)
    public void checkOwner(Commento commentoOld, String username) {
        if(!commentoOld.getAutore().equals(username)){
            throw new RuntimeException("Non autorizzato");
        }
    }

    public void save(Commento commento) {
        this.commentoRepository.save(commento);
    }
}
