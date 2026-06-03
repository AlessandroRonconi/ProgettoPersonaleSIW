package it.uniroma3.siw.progetto_personale_siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.progetto_personale_siw.model.Corso;

@Repository
public interface CorsoRepository extends CrudRepository<Corso, Long> {

    @Query("""
                SELECT c FROM Corso c
                JOIN c.weekdayOrario orario
                WHERE KEY(orario) = :giorno
                AND VALUE(orario) = :orario
                AND c.id <> :excludeId
            """)
    List<Corso> findByGiornoAndOrarioExcluding(
            @Param("giorno") String giorno,
            @Param("orario") String orario,
            @Param("excludeId") Long excludeId);
}
