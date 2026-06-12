package it.uniroma3.siw.progetto_personale_siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progetto_personale_siw.model.Esercizio;

public interface EsercizioRepository extends CrudRepository<Esercizio, Long> {

    boolean existsByNome(String nome);

}
