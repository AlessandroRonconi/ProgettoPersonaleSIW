package it.uniroma3.siw.progetto_personale_siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class SchedaAllenamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "La data di inizio è obbligatoria")
    @Future(message = "la data di inizio deve essere futura")
    @Column(nullable = false)
    private LocalDate dataInizio;

    
    @NotNull(message = "La data di inizio è obbligatoria")
    @Future(message = "la data di fine deve essere futura")
    @Column(nullable = false)
    private LocalDate dataFine;

    @NotBlank(message = "L'obiettivo è obbligatorio")
    @Size(min = 3, max = 100, message = "L'obiettivo deve essere tra 3 e 100 caratteri")
    @Column(nullable = false)
    private String obiettivo;

    @Size(max = 1000, message = "Le note non possono superare 1000 caratteri")
    @Column(length = 1000)
    private String note;

    @ManyToOne // FK id_user in relazione schedaall, qui schedaall è owner
    private User user;

    @ManyToOne // FK id_pt in relazione schedaall, qui schedaall è owner
    private PersonalTrainer pt;

    /*@ManyToMany // new table, è owner
    private List<Esercizio> esercizi = new ArrayList<>();*/

    @OneToMany(mappedBy = "scheda", cascade = CascadeType.ALL) //se salvo/elimino la scheda si salvano/eliminano i commenti
    private List<CommentoScheda> commenti = new ArrayList<>();

    @OneToMany(mappedBy = "scheda")
    private List<EsercizioScheda> eserciziScheda = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PersonalTrainer getPt() {
        return pt;
    }

    public void setPt(PersonalTrainer pt) {
        this.pt = pt;
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
        SchedaAllenamento other = (SchedaAllenamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public List<CommentoScheda> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<CommentoScheda> commenti) {
        this.commenti = commenti;
    }

    public List<EsercizioScheda> getEserciziScheda() {
        return eserciziScheda;
    }

    public void setEserciziScheda(List<EsercizioScheda> eserciziScheda) {
        this.eserciziScheda = eserciziScheda;
    }

    // questo qui sotto non l'ho benissimo capito non penso serva?
    /*
     * @ElementCollection
     * 
     * @CollectionTable(name = "schema_giorni_settimana", joinColumns
     * = @JoinColumn(name = "schema_id"))
     * 
     * @Column(name = "giorno")
     * private List<String> giorniAllenamento; // Es: ["LUNEDI", "MERCOLEDI",
     * "VENERDI"]
     */
}
