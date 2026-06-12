package it.uniroma3.siw.progetto_personale_siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progetto_personale_siw.model.Commento;

public interface CommentoRepository extends CrudRepository<Commento, Long> {

    List<Commento> findByCorsoId(Long id);

}
