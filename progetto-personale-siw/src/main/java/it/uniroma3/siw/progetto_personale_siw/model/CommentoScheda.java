package it.uniroma3.siw.progetto_personale_siw.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class CommentoScheda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Il commento non può essere vuoto")
    @Size(min = 5, max = 100, message = "Il commento deve essere tra 5 e 100 caratteri")
    @Column(nullable = false)
    public String testo;

    @Column(nullable = false)
    public String autore; // è lo username

    @Column(nullable = false)
    public LocalDateTime dataOra;

    @ManyToOne
    private User user;

    @ManyToOne
    private SchedaAllenamento scheda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SchedaAllenamento getScheda() {
        return scheda;
    }

    public void setScheda(SchedaAllenamento scheda) {
        this.scheda = scheda;
    }
}

