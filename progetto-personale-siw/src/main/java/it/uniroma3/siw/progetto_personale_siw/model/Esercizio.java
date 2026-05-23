package it.uniroma3.siw.progetto_personale_siw.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Esercizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome dell'esercizio è obbligatorio")
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 500)
    private String descrizione;

    @NotBlank(message = "Le serie sono obbligatorie")
    @Positive(message = "Le serie devono essere un numero positivo")
    @Column(nullable = false)
    private Integer serie;

    @NotBlank(message = "Le ripetizioni sono obbligatorie")
    @Positive(message = "Le ripetizioni devono essere un numero positivo")
    @Column(nullable = false)
    private Integer ripetizioni;

    @Min(value = 0, message = "Il recupero non può essere negativo")
    private Integer recuperoSec;

    @ManyToMany(mappedBy = "esercizi")
    private List<SchedaAllenamento> schede = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    public List<SchedaAllenamento> getSchede() {
        return schede;
    }

    public void setSchede(List<SchedaAllenamento> schede) {
        this.schede = schede;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Esercizio other = (Esercizio) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
