package it.uniroma3.siw.progetto_personale_siw.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Il nome del corso è obbligatorio")
    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 500)
    private String descrizione;

    @NotNull(message = "La durata della lezione è obbligatoria")
    @Positive(message = "La durata deve essere positiva")
    @Column(nullable = false)
    private Integer durataLezione; // in minuti

    @NotBlank(message = "Il livello è obbligatorio")
    @Column(nullable = false)
    private String livello;

    @NotNull(message = "La capacità massima è obbligatoria")
    @Min(value = 1, message = "La capacità minima è 1")
    @Column(nullable = false)
    private Integer capacita;

    // mappa giorno della settimana-orario es lunedi -> 09:00-11:00
    @ElementCollection
    @CollectionTable(name = "corso_orari", joinColumns = @JoinColumn(name = "corso_id"))
    @MapKeyColumn(name = "giorno")
    @Column(name = "orario")
    private Map<String, String> weekdayOrario = new HashMap<>();

    @ManyToOne
    private Istruttore istruttore;

    @OneToMany(mappedBy = "corso")
    private List<Commento> commenti;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.REMOVE)
    private List<Prenotazione> prenotazioni;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getDurataLezione() {
        return durataLezione;
    }

    public void setDurataLezione(Integer durataLezione) {
        this.durataLezione = durataLezione;
    }

    public String getLivello() {
        return livello;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }

    public Integer getCapacita() {
        return capacita;
    }

    public void setCapacita(Integer capacita) {
        this.capacita = capacita;
    }

    public Map<String, String> getWeekdayOrario() {
        return weekdayOrario;
    }

    public void setWeekdayOrario(Map<String, String> weekdayOrario) {
        this.weekdayOrario = weekdayOrario;
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
        Corso other = (Corso) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Istruttore getIstruttore() {
        return istruttore;
    }

    public void setIstruttore(Istruttore istruttore) {
        this.istruttore = istruttore;
    }

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

}
