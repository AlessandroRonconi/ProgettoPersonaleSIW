package it.uniroma3.siw.progetto_personale_siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progetto_personale_siw.model.Abbonamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;

public interface AbbonamentoRepository extends CrudRepository<Abbonamento, Long> {

    Abbonamento findByUser(User user);

}
