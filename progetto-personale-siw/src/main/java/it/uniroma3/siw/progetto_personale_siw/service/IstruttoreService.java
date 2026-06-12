package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.Istruttore;
import it.uniroma3.siw.progetto_personale_siw.repository.IstruttoreRepository;

@Service
@Transactional
public class IstruttoreService {

    private  IstruttoreRepository istruttoreRepository;

    IstruttoreService(IstruttoreRepository istruttoreRepository) {
        this.istruttoreRepository = istruttoreRepository;
    }

    @Transactional(readOnly = true)
    public List<Istruttore> findAll() {
        return (List<Istruttore>) this.istruttoreRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Istruttore> findById(Long id) {
        return this.istruttoreRepository.findById(id);
    }

}
