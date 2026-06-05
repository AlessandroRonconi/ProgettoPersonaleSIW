package it.uniroma3.siw.progetto_personale_siw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Credentials → login (username, password, ruolo)
@Entity
public class Credentials {
  public static final String DEFAULT_ROLE = "DEFAULT";
  public static final String USER_ROLE = "USER";
  public static final String ADMIN_ROLE = "ADMIN";
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Username obbligatorio") // Validazione prima di salvare
  @Size(min = 3, max = 20, message = "Username deve essere tra 3 e 20 caratteri") // Validazione prima di salvare
                                                                                  // livello di JAVA
  @Column(nullable = false, unique = true) // Vincolo dopo che JPA prova a salvare livello di DB
  private String username;

  @NotBlank(message = "Password obbligatoria")
  @Size(min = 6, message = "Password deve avere almeno 6 caratteri")
  @Column(nullable = false, unique = false)
  private String password;
  private String ruolo;

  @Valid
  @OneToOne(cascade = CascadeType.ALL) // significa che se salvi credential salvi anche utente
  private User user;

  public static String getDefaultRole() {
    return DEFAULT_ROLE;
  }

  public static String getUserRole() {
    return USER_ROLE;
  }

  public static String getAdminRole() {
    return ADMIN_ROLE;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRuolo() {
    return ruolo;
  }

  public void setRuolo(String ruolo) {
    this.ruolo = ruolo;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
    Credentials other = (Credentials) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
