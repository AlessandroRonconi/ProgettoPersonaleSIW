package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateEsercizioException;
import it.uniroma3.siw.progetto_personale_siw.model.Esercizio;
import it.uniroma3.siw.progetto_personale_siw.repository.EsercizioRepository;

@Service
@Transactional
public class EsercizioService {

    private final EsercizioRepository esercizioRepository;

    EsercizioService(EsercizioRepository esercizioRepository) {
        this.esercizioRepository = esercizioRepository;
    }

    public List<Esercizio> findAll() {
        return (List<Esercizio>) this.esercizioRepository.findAll();
    }

    public void save(Esercizio esercizio) {
        if(this.esercizioRepository.existsByNome(esercizio.getNome())){
            throw new DuplicateEsercizioException("Esercizio con nome '" + esercizio.getNome() + "' già esistente!");
        }
        this.esercizioRepository.save(esercizio);
    }

    /*public void deleteEsercizio(Long esercizioId) {
        Esercizio esercizio = this.esercizioRepository.findById(esercizioId).orElseThrow(() -> new ResourceNotFoundException("Esercizio non trovato"));
        esercizio.set
    }*/

    public void deleteById(Long esercizioId) {
        this.esercizioRepository.deleteById(esercizioId);
    }

}
