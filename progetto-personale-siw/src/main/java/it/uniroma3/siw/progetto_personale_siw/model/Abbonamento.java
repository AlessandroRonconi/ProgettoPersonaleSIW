package it.uniroma3.siw.progetto_personale_siw.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
public class Abbonamento {// quello del singolo utente

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "la data di inizio è obbligatoria")
    @FutureOrPresent(message = "la data deve essere nel futuro")
    @Column(nullable = false)
    private LocalDate dataInizio;

    @NotNull(message = "la data di fine è obbligatoria")
    @Future(message = "la data deve essere nel futuro")
    @Column(nullable = false)
    private LocalDate dataFine;

    @NotNull(message = "il tipo è obbligatorio")
    @JoinColumn(name = "tipo_di_abbonamento_id")
    @ManyToOne
    private TipoAbbonamento tipoDiAbbonamento;

    @OneToOne // owner
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TipoAbbonamento getTipoDiAbbonamento() {
        return tipoDiAbbonamento;
    }

    public void setTipoDiAbbonamento(TipoAbbonamento tipoDiAbbonamento) {
        this.tipoDiAbbonamento = tipoDiAbbonamento;
    }

}
