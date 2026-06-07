package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.Abbonamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.AbbonamentoRepository;

@Service
@Transactional
public class AbbonamentoService {

    private  AbbonamentoRepository abbonamentoRepository;
    public AbbonamentoService(AbbonamentoRepository abbonamentoRepository){
        this.abbonamentoRepository = abbonamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<Abbonamento> findAll() {
        return (List<Abbonamento>) this.abbonamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Abbonamento findByUser(User user) {
        return this.abbonamentoRepository.findByUser(user);
    }

}
