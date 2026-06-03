package it.uniroma3.siw.progetto_personale_siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;

@Repository
public interface SchedaAllenamentoRepository extends CrudRepository<SchedaAllenamento, Long> {

    /*Senza fetch join: Carichi Scheda esercizi NON caricati subito commenti NON caricati subito li carica dopo (lazy)
    risultato: altre query mentre fai il render HTML
    Con fetch join: Carichi Scheda + esercizi + commenti TUTTO insieme */
    //questo sotto è fetch join implemntato
    /*@Query("""
    SELECT DISTINCT s
    FROM SchedaAllenamento s
    LEFT JOIN FETCH s.esercizi
    LEFT JOIN FETCH s.commenti
    WHERE s.user = :user
    """)
    List<SchedaAllenamento> findByUserWithDetails(@Param("user") User user);*/
    //nel service
    /*public List<SchedaAllenamento> findByUserWithDetails(User user) {
    return schedaRepository.findByUserWithDetails(user);
    } */
    /*in controller prima findByUser(user) poi findByUserWithDetails(user) */
    List<SchedaAllenamento> findByUser(User user);

    boolean existsByUserAndDataInizioAndDataFine(User user, LocalDate dataInizio, LocalDate dataFine);

    boolean existsByUserAndDataInizioAndDataFineAndIdNot(User user, LocalDate dataInizio, LocalDate dataFine,
            Long idSchedaOld);

   

}
