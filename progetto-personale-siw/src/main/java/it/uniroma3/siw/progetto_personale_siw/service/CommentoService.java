package it.uniroma3.siw.progetto_personale_siw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.repository.CommentoRepository;

@Service
@Transactional
public class CommentoService {

    private CommentoRepository commentoRepository;
    public CommentoService(CommentoRepository commentoRepository){
        this.commentoRepository = commentoRepository;
    }

    public Object getCommentiByCorsoId(Long id) {
        return this.commentoRepository.findByCorsoId(id);
    }

}
