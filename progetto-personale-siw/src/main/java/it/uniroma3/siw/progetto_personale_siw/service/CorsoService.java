package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.repository.CorsoRepository;

@Service
@Transactional
public class CorsoService {

    private CorsoRepository corsoRepository;

    public CorsoService(CorsoRepository corsoRepository) {
        this.corsoRepository = corsoRepository;
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
    public void save(Corso corso) {
        this.corsoRepository.save(corso);
    }

    @Transactional
    public void deleteById(Long id) {
        this.corsoRepository.deleteById(id);
    }

}
