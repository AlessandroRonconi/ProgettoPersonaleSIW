package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.CorsoOrarioConflictException;
import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.repository.CorsoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.IstruttoreRepository;

@Service
@Transactional
public class CorsoService {

    private CorsoRepository corsoRepository;
    private IstruttoreRepository istruttoreRepository;

    public CorsoService(CorsoRepository corsoRepository, IstruttoreRepository istruttoreRepository) {
        this.corsoRepository = corsoRepository;
        this.istruttoreRepository = istruttoreRepository;
    }

    @Transactional(readOnly = true)
    public List<Corso> findAll() {
        return (List<Corso>) this.corsoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Corso findById(Long id) {
        return corsoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("corso non trovato"));
    }

    @Transactional
    public void update(Corso corsoForm, Long corsoId, Long istruttoreId) {
        Corso corsoEsistente = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new ResourceNotFoundException("corso non trovato"));

        corsoEsistente.setNome(corsoForm.getNome());
        corsoEsistente.setDescrizione(corsoForm.getDescrizione());
        corsoEsistente.setDurataLezione(corsoForm.getDurataLezione());
        corsoEsistente.setLivello(corsoForm.getLivello());
        corsoEsistente.setCapacita(corsoForm.getCapacita());
        corsoEsistente.setWeekdayOrario(corsoForm.getWeekdayOrario());
        corsoEsistente.getWeekdayOrario().values().removeIf(String::isBlank);

        // Escludiamo il corso stesso dal controllo conflitti
        this.checkOrariConflict(corsoEsistente.getWeekdayOrario(), corsoId);

        if (istruttoreId != null && istruttoreId > 0) {
            istruttoreRepository.findById(istruttoreId)
                    .ifPresent(corsoEsistente::setIstruttore);
        } else {
            corsoEsistente.setIstruttore(null);
        }

        corsoRepository.save(corsoEsistente);
    }

    @Transactional
    public void deleteById(Long id) {
        this.corsoRepository.deleteById(id);
    }

    public void save(Corso corso) {
        // Per un nuovo corso l'id è null: usiamo -1L come excludeId così la query non
        // esclude nessun corso esistente
        Long excludeId = corso.getId() != null ? corso.getId() : -1L;
        this.checkOrariConflict(corso.getWeekdayOrario(), excludeId);
        this.corsoRepository.save(corso);
    }

    private void checkOrariConflict(Map<String, String> weekdayOrario, Long excludeId) {
        for (Map.Entry<String, String> entry : weekdayOrario.entrySet()) {
            String giorno = entry.getKey();
            String orario = entry.getValue();
            if (orario == null || orario.isBlank())
                continue;

            List<Corso> conflitti = corsoRepository.findByGiornoAndOrarioExcluding(giorno, orario, excludeId);

            if (!conflitti.isEmpty())
                throw new CorsoOrarioConflictException(giorno, orario, conflitti.get(0).getNome());
        }
    }

}
