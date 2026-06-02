package it.uniroma3.siw.progetto_personale_siw.service;

import java.time.LocalDate;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateSchedaAllenamentoException;
import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.PersonalTrainer;
import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.PersonalTrainerRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.SchedaAllenamentoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.UserRepository;

@Service
@Transactional
public class SchedaAllenamentoService {

    private SchedaAllenamentoRepository schedaAllenamentoRepository;
    private UserRepository userRepository;
    private PersonalTrainerRepository personalTrainerRepository;
    public SchedaAllenamentoService(SchedaAllenamentoRepository schedaAllenamentoRepository, UserRepository userRepository, PersonalTrainerRepository personalTrainerRepository){
        this.schedaAllenamentoRepository = schedaAllenamentoRepository;
        this.userRepository = userRepository;
        this.personalTrainerRepository = personalTrainerRepository;
    }
    public List<SchedaAllenamento> findByUser(User user) {
        return this.schedaAllenamentoRepository.findByUser(user);
    }

    @Transactional
    public void creaScheda(SchedaAllenamento scheda,Long userId,Long ptId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));
        scheda.setUser(user);
        if (ptId != null) {
            PersonalTrainer pt = personalTrainerRepository.findById(ptId).orElseThrow(() -> new ResourceNotFoundException("PT non trovato"));
            scheda.setPt(pt);
        }
        boolean exists = schedaAllenamentoRepository.existsByUserAndPtAndDataInizioAndDataFineAndObiettivo(user, scheda.getPt(), scheda.getDataInizio(), scheda.getDataFine(), scheda.getObiettivo());

        if (exists) {
            throw new DuplicateSchedaAllenamentoException("Esiste già una scheda con gli stessi dati");
        }
        schedaAllenamentoRepository.save(scheda);
    }
    @Transactional(readOnly = true)
    public List<SchedaAllenamento> findAll() {
        return (List<SchedaAllenamento>) this.schedaAllenamentoRepository.findAll();
    }

}
