package it.uniroma3.siw.progetto_personale_siw.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class TipoAbbonamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "deve avere un nome")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "deve esserci un prezzo")
    @Min(value = 0, message = "il prezzo deve essere posittivo")
    @Column(nullable = false)
    private Integer prezzo;

    @NotNull(message = "deve avere una durata")
    @Column(name = "durataInMesi", nullable = false)
    private Integer durataInMesi;

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

    public Integer getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Integer prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getDurataInMesi() {
        return durataInMesi;
    }

    public void setDurataInMesi(Integer durataInMesi) {
        this.durataInMesi = durataInMesi;
    }
}
