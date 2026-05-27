package it.uniroma3.siw.progetto_personale_siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.progetto_personale_siw.model.Corso;
import it.uniroma3.siw.progetto_personale_siw.model.Prenotazione;
import it.uniroma3.siw.progetto_personale_siw.model.User;

@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {

    long countByCorso(Corso corso);

    boolean existsByUserAndCorso(User user, Corso corso);

    List<Prenotazione> findByUser(User user);

}
