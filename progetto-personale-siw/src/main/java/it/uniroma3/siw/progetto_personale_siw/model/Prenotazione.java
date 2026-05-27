package it.uniroma3.siw.progetto_personale_siw.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "La data e ora è obbligatoria")
    @Future(message = "La prenotazione deve essere per una data futura")
    @Column(nullable = false)
    private LocalDateTime dataOra;

    @Column(name = "data_prenotazione", updatable = false)
    private LocalDateTime dataPrenotazione = LocalDateTime.now();

    @ManyToOne // FK id_user in relazione prenotazione, qui prenotazione è owner
    private User user;

    @ManyToOne // FK id_pt in relazione prenotazione, qui prenotazione è owner
    private Corso corso;

    /*
     * @Enumerated(EnumType.STRING) dice è utile se mi seerve uno storico, ho
     * chiesto a chat
     * ma non penso a noi serva una cosa del genere?
     * 
     * @Column(nullable = false)
     * private StatoPrenotazione stato = StatoPrenotazione.CONFERMATA;
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public LocalDateTime getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDateTime dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
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
}
