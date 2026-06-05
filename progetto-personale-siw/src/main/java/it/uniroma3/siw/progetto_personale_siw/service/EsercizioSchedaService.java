package it.uniroma3.siw.progetto_personale_siw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.Esercizio;
import it.uniroma3.siw.progetto_personale_siw.model.EsercizioScheda;
import it.uniroma3.siw.progetto_personale_siw.model.SchedaAllenamento;
import it.uniroma3.siw.progetto_personale_siw.repository.EsercizioRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.EsercizioSchedaRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.SchedaAllenamentoRepository;

@Service
@Transactional
public class EsercizioSchedaService {
    private  EsercizioRepository esercizioRepository;
    private  SchedaAllenamentoRepository schedaAllenamentoRepository;
    private  EsercizioSchedaRepository esercizioSchedaRepository;

    EsercizioSchedaService(EsercizioSchedaRepository esercizioSchedaRepository,
            SchedaAllenamentoRepository schedaAllenamentoRepository, EsercizioRepository esercizioRepository) {
        this.schedaAllenamentoRepository = schedaAllenamentoRepository;
        this.esercizioRepository = esercizioRepository;
        this.esercizioSchedaRepository = esercizioSchedaRepository;
    }

    public void save(Long id, Long esercizioId, Integer serie, Integer ripetizioni, Integer recuperoSec) {
        SchedaAllenamento scheda = this.schedaAllenamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scheda non trovata"));
        Esercizio esercizio = this.esercizioRepository.findById(esercizioId)
                .orElseThrow(() -> new ResourceNotFoundException("Esercizio non trovato"));

        EsercizioScheda esercizioScheda = new EsercizioScheda();
        esercizioScheda.setScheda(scheda);
        esercizioScheda.setEsercizio(esercizio);
        esercizioScheda.setSerie(serie);
        esercizioScheda.setRipetizioni(ripetizioni);
        esercizioScheda.setRecuperoSec(recuperoSec);

        esercizioSchedaRepository.save(esercizioScheda);
    }

    public void eliminaEsDaScheda(Long eserciziSchedaId) {
        this.esercizioSchedaRepository.deleteById(eserciziSchedaId);
    }

   

}
