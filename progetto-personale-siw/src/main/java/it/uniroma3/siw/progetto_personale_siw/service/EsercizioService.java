package it.uniroma3.siw.progetto_personale_siw.service;

import it.uniroma3.siw.progetto_personale_siw.repository.EsercizioRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.Esercizio;

@Service
@Transactional
public class EsercizioService {

    private EsercizioRepository esercizioRepository;

    EsercizioService(EsercizioRepository esercizioRepository) {
        this.esercizioRepository = esercizioRepository;
    }

    public List<Esercizio> findAll() {
        return (List<Esercizio>) this.esercizioRepository.findAll();
    }

    public void save(Esercizio esercizio) {
        this.esercizioRepository.save(esercizio);
    }

}
