package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.SchedaAllenamentoRepository;

@Service
@Transactional
public class SchedaAllenamentoService {

    SchedaAllenamentoRepository schedaAllenamentoRepository;
    public SchedaAllenamentoService(SchedaAllenamentoRepository schedaAllenamentoRepository){
        this.schedaAllenamentoRepository = schedaAllenamentoRepository;
    }
    public List<SchedaAllenamento> findByUser(User user) {
        return this.schedaAllenamentoRepository.findByUser(user);
    }

}
