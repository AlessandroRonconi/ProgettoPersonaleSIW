package it.uniroma3.siw.progetto_personale_siw.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.model.Prenotazione;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.CorsoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.PrenotazioneRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.UserRepository;

@Service
@Transactional
public class PrenotazioneService {

    private  UserRepository userRepository;
    private  CorsoRepository corsoRepository;
    private  PrenotazioneRepository prenotazioneRepository;

    public PrenotazioneService(CorsoRepository corsoRepository, PrenotazioneRepository prenotazioneRepository,
            UserRepository userRepository) {
        this.userRepository = userRepository;
        this.prenotazioneRepository = prenotazioneRepository;
        this.corsoRepository = corsoRepository;
    }

    public void prenota(Long userId, Long corsoId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));
        Corso corso = this.corsoRepository.findById(corsoId)
                .orElseThrow(() -> new ResourceNotFoundException("Corso non trovato"));
                
        Long iscritti = this.prenotazioneRepository.countByCorso(corso);
        if (iscritti >= corso.getCapacita()) {
            throw new RuntimeException("Corso Pieno");
        }

        boolean esistePrenotazione = this.prenotazioneRepository.existsByUserAndCorso(user, corso);
        if (esistePrenotazione) {
            throw new RuntimeException("Esiste gia prenotazione");
        }
        Prenotazione p = new Prenotazione();
        p.setUser(user);
        p.setCorso(corso);
        p.setDataOra(LocalDateTime.now());

        prenotazioneRepository.save(p);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> findByUser(User user) {
        return prenotazioneRepository.findByUser(user);
    }

    public void deleteById(Long id) {
        this.prenotazioneRepository.deleteById(id);
    }

}
