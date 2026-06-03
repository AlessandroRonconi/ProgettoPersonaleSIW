package it.uniroma3.siw.progetto_personale_siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.progetto_personale_siw.model.Esercizio;

@Repository
public interface EsercizioRepository extends CrudRepository<Esercizio, Long> {


    boolean existsByNome(String nome);

}
