package it.uniroma3.siw.progetto_personale_siw.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PersonalTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Il nome è obbligatorio")
    @Size(min = 3, message = "Il nome deve avere almeno 3 lettere")
    @Column(nullable = false)
    private String nome;
    
    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(min = 4, max = 50, message = "Il cognome deve avere almeno 4 lettere")
    @Column(nullable = false)
    private String cognome;
    
    @NotBlank(message = "La specialità è obbligatoria")
    @Column(nullable = false)
    private String specialita;  // crossfit, bb (bodybuilding), yoga, pilates, etc.
    
    
    @Min(value = 0, message = "La tariffa oraria minima è 0€")
    @Column(nullable = false)
    private Integer tariffaOraria;
    
    @OneToMany(mappedBy = "pt", cascade = CascadeType.ALL)
    private List<SchedaAllenamento> schedeAllenamento = new ArrayList<>();

    @OneToMany(mappedBy = "pt")
    private List<User> users = new ArrayList<>();

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSpecialita() {
        return specialita;
    }

    public void setSpecialita(String specialita) {
        this.specialita = specialita;
    }

    public Integer getTariffaOraria() {
        return tariffaOraria;
    }

    public void setTariffaOraria(Integer tariffaOraria) {
        this.tariffaOraria = tariffaOraria;
    }

    

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
        PersonalTrainer other = (PersonalTrainer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public List<SchedaAllenamento> getSchedeAllenamento() {
        return schedeAllenamento;
    }

    public void setSchedeAllenamento(List<SchedaAllenamento> schedeAllenamento) {
        this.schedeAllenamento = schedeAllenamento;
    }
}
