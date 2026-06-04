package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.TipoAbbonamento;
import it.uniroma3.siw.progetto_personale_siw.repository.TipoAbbonamentoRepository;

@Service
@Transactional
public class TipoAbbonamentoService {
    private final TipoAbbonamentoRepository tipoAbbonamentoRepository;

    public TipoAbbonamentoService(TipoAbbonamentoRepository tipoAbbonamentoRepository) {
        this.tipoAbbonamentoRepository = tipoAbbonamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<TipoAbbonamento> findAll() {
        return (List<TipoAbbonamento>) this.tipoAbbonamentoRepository.findAll();
    }

}
