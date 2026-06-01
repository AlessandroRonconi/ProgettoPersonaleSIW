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
}

