package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void update(Corso corsoForm, Long CorsoId, Long istruttoreId) {
        Corso corsoEsistente = corsoRepository.findById(CorsoId).orElseThrow(() -> new ResourceNotFoundException("corso non trovato"));
        corsoEsistente.setNome(corsoForm.getNome());
        corsoEsistente.setDescrizione(corsoForm.getDescrizione());
        corsoEsistente.setDurataLezione(corsoForm.getDurataLezione());
        corsoEsistente.setLivello(corsoForm.getLivello());
        corsoEsistente.setCapacita(corsoForm.getCapacita());
        corsoEsistente.setWeekdayOrario(corsoForm.getWeekdayOrario());
        corsoEsistente.getWeekdayOrario().values().removeIf(String::isBlank);
        if (istruttoreId != null && istruttoreId > 0) {
            istruttoreRepository.findById(istruttoreId).ifPresent(corsoEsistente::setIstruttore);
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
        this.corsoRepository.save(corso);
    }

}
