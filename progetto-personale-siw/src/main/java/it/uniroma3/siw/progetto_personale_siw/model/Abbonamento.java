package it.uniroma3.siw.progetto_personale_siw.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "il nome è obbligatorioo")
    @Column(nullable = false)
    private String tipoDiAbbonamento;

    @NotBlank(message = "la data di inizio è obbligatoria")
    @Future(message = "la data deve essere nel futuro")
    @Column(nullable = false)
    private LocalDate dataInizio;

    @NotBlank(message = "la data di fine è obbligatoria")
    @Future(message = "la data deve essere nel futuro")
    @Column(nullable = false)
    private LocalDate dataFine;

    @NotBlank(message = "deve esserci un prezzo")
    @Min(value = 0, message = "il prezzo deve essere posittivo")
    @Column(nullable = false)
    private Integer prezzo;

    @NotBlank(message = "deve avere una durata")
    @Column(name = "durataInMesi", nullable = false)
    private Integer durataInMesi;

    @OneToMany(mappedBy = "abbonamento")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
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
        Abbonamento other = (Abbonamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Integer getDurataInMesi() {
        return durataInMesi;
    }

    public void setDurataInMesi(Integer durataInMesi) {
        this.durataInMesi = durataInMesi;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getTipoDiAbbonamento() {
        return tipoDiAbbonamento;
    }

    public void setTipoDiAbbonamento(String tipoDiAbbonamento) {
        this.tipoDiAbbonamento = tipoDiAbbonamento;
    }

}
