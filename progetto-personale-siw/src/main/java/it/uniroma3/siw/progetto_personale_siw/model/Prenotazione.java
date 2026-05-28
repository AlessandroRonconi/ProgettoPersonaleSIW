package it.uniroma3.siw.progetto_personale_siw.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "prenotazione", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "corso_id" }) })
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_prenotazione", updatable = false)
    private LocalDateTime dataOra = LocalDateTime.now();

    @ManyToOne // FK id_user in relazione prenotazione, qui prenotazione è owner
    private User user;

    @ManyToOne // FK id_pt in relazione prenotazione, qui prenotazione è owner
    private Corso corso;

    /*
     * @Enumerated(EnumType.STRING) dice è utile se mi seerve uno storico, ho
     * chiesto a chat
     * ma non penso a noi serva una cosa del genere?
     * no non serve
     * 
     * @Column(nullable = false)
     * private StatoPrenotazione stato = StatoPrenotazione.CONFERMATA;
     * 
     * ma no dai non serve neanche lo stato
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
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
        Prenotazione other = (Prenotazione) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }
}
