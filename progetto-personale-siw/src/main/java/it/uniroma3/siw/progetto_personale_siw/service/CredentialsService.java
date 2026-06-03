package it.uniroma3.siw.progetto_personale_siw.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.exception.DuplicateCredentialsException;
import it.uniroma3.siw.progetto_personale_siw.exception.ResourceNotFoundException;
import it.uniroma3.siw.progetto_personale_siw.model.Abbonamento;
import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.TipoAbbonamento;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.AbbonamentoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.CredentialsRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.TipoAbbonamentoRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.UserRepository;

@Service
@Transactional
public class CredentialsService {

    private final PasswordEncoder passwordEncoder;
    private TipoAbbonamentoRepository tipoAbbonamentoRepository;
    private CredentialsRepository credentialsRepository;
    private UserRepository userRepository;
    private AbbonamentoRepository abbonamentoRepository;
    public CredentialsService(AbbonamentoRepository abbonamentoRepository, TipoAbbonamentoRepository tipoAbbonamentoRepository, CredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tipoAbbonamentoRepository = tipoAbbonamentoRepository;
        this.abbonamentoRepository = abbonamentoRepository;
    }


    public void save(Credentials credentials, Long abbonamentoId) {//da finire con gli abbonamenti
        if (credentialsRepository.existsByUsername(credentials.getUsername())) {
            throw new DuplicateCredentialsException("Username '" + credentials.getUsername() + "' già esistente. Scegline un altro.");
        }
        User user = credentials.getUser();
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateCredentialsException("Email '" + user.getEmail() + "' già registrata. Usane un'altra.");
        }
        
        TipoAbbonamento tipo = this.tipoAbbonamentoRepository.findById(abbonamentoId).orElseThrow(() -> new ResourceNotFoundException("Abbonamento non trovato"));
        
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setTipoDiAbbonamento(tipo);
        abbonamento.setUser(user);
        abbonamento.setDataInizio(LocalDate.now());
        abbonamento.setDataFine(LocalDate.now().plusMonths(tipo.getDurataInMesi()));

System.out.println("=== VALORI ABBONAMENTO ===");
System.out.println("tipoDiAbbonamento ID: " + (abbonamento.getTipoDiAbbonamento() != null ? abbonamento.getTipoDiAbbonamento().getId() : "NULL"));
System.out.println("user ID: " + (abbonamento.getUser() != null ? abbonamento.getUser().getId() : "NULL"));
System.out.println("dataInizio: " + abbonamento.getDataInizio());
System.out.println("dataFine: " + abbonamento.getDataFine());
System.out.println("=========================");
try {
        System.out.println("[DEBUG] Tentativo salvataggio abbonamento...");
        this.abbonamentoRepository.save(abbonamento);
        System.out.println("[DEBUG] Abbonamento salvato con successo! ID: " + abbonamento.getId());
    } catch (Exception e) {
        System.err.println("[DEBUG] ERRORE salvataggio abbonamento: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
        //this.abbonamentoRepository.save(abbonamento);
        
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentials.setRuolo(Credentials.USER_ROLE);

System.out.println("=== VALORI CREDENTIALS ===");
    System.out.println("username: " + credentials.getUsername());
    System.out.println("ruolo: " + credentials.getRuolo());
    System.out.println("user email: " + (credentials.getUser() != null ? credentials.getUser().getEmail() : "NULL"));
    System.out.println("=========================");
    
    // Salva credentials
    try {
        System.out.println("[DEBUG] Tentativo salvataggio credentials...");
        this.credentialsRepository.save(credentials);
        System.out.println("[DEBUG] Credentials salvate con successo!");
    } catch (Exception e) {
        System.err.println("[DEBUG] ERRORE salvataggio credentials: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
    
    System.out.println("=== [DEBUG] END save - TUTTO OK ===");
        //this.credentialsRepository.save(credentials);
    }

    @Transactional(readOnly = true)
    public Credentials getCredentials(String username) {
        return credentialsRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));
    }

}
