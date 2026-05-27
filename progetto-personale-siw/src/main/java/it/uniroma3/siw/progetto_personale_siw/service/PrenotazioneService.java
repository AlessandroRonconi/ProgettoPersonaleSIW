package it.uniroma3.siw.progetto_personale_siw.service;


import java.time.LocalDateTime;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.model.Prenotazione;
import it.uniroma3.siw.progetto_personale_siw.model.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.CorsoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.PrenotazioneRepository;

@Service
@Transactional
public class PrenotazioneService {

    private CorsoRepository corsoRepository;
    private PrenotazioneRepository prenotazioneRepository;
    public PrenotazioneService(CorsoRepository corsoRepository, PrenotazioneRepository prenotazioneRepository){
        this.prenotazioneRepository = prenotazioneRepository;
        this.corsoRepository = corsoRepository;
    }
    public void prenota(User user, Long corsoId) {
        Corso corso = this.corsoRepository.findById(corsoId).orElseThrow(() -> new ResourceNotFoundException("Corso non trovato"));
        long iscritti = this.prenotazioneRepository.countByCorso(corso);
        if(iscritti >= corso.getCapacita()){
            throw new RuntimeException("Corso Pieno");
        }

        boolean esistePrenotazione = this.prenotazioneRepository.existsByUserAndCorso(user,corso);
        if(esistePrenotazione){
            throw new RuntimeException("Esiste gia prenotazione");
        }
        Prenotazione p = new Prenotazione();
        p.setUser(user);
        p.setCorso(corso);
        p.setDataOra(LocalDateTime.now());

        prenotazioneRepository.save(p);
    }
    public List<Prenotazione> findByUser(User user) {
        return prenotazioneRepository.findByUser(user);
}

}
