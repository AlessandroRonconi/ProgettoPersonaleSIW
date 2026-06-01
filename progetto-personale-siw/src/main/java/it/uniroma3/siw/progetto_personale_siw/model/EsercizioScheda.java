package it.uniroma3.siw.progetto_personale_siw.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class EsercizioScheda {//è l'esercizio che appartiene alla scheda con le serie, rep, e secondi di recupero
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Le serie sono obbligatorie")
    @Positive(message = "Le serie devono essere un numero positivo")
    @Column(nullable = false)
    private Integer serie;

    @NotNull(message = "Le ripetizioni sono obbligatorie")
    @Positive(message = "Le ripetizioni devono essere un numero positivo")
    @Column(nullable = false)
    private Integer ripetizioni;

    @Min(value = 0, message = "Il recupero non può essere negativo")
    private Integer recuperoSec;

    @ManyToOne
    private SchedaAllenamento scheda;

    @ManyToOne
    private Esercizio esercizio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(Integer ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public Integer getRecuperoSec() {
        return recuperoSec;
    }

    public void setRecuperoSec(Integer recuperoSec) {
        this.recuperoSec = recuperoSec;
    }

    public SchedaAllenamento getScheda() {
        return scheda;
    }

    public void setScheda(SchedaAllenamento scheda) {
        this.scheda = scheda;
    }

    public Esercizio getEsercizio() {
        return esercizio;
    }

    public void setEsercizio(Esercizio esercizio) {
        this.esercizio = esercizio;
    }
}
