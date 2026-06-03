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

    public CredentialsService(AbbonamentoRepository abbonamentoRepository,
            TipoAbbonamentoRepository tipoAbbonamentoRepository, CredentialsRepository credentialsRepository,
            PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tipoAbbonamentoRepository = tipoAbbonamentoRepository;
        this.abbonamentoRepository = abbonamentoRepository;
    }

    @Transactional
    public void save(Credentials credentials, Long TipoAbbonamentoId) {

        if (credentialsRepository.existsByUsername(credentials.getUsername())) {
            throw new DuplicateCredentialsException(
                    "Username '" + credentials.getUsername() + "' già esistente. Scegline un altro.");
        }

        User user = credentials.getUser();

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateCredentialsException(
                    "Email '" + user.getEmail() + "' già registrata. Usane un'altra.");
        }

        TipoAbbonamento tipo = tipoAbbonamentoRepository.findById(TipoAbbonamentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo di abbonamento non trovato"));

        // sincronizzazione relazione Credentials <-> User
        user.setCredentials(credentials);
        credentials.setUser(user);

        // 1. salva USER (cascade da Credentials potrebbe bastare, ma qui esplicito)
        userRepository.save(user);

        // 2. salva CREDENTIALS
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentials.setRuolo(Credentials.USER_ROLE);
        credentialsRepository.save(credentials);

        // 3. crea e salva ABBONAMENTO
        Abbonamento abbonamento = new Abbonamento();
        abbonamento.setTipoDiAbbonamento(tipo);
        abbonamento.setUser(user);
        abbonamento.setDataInizio(LocalDate.now());
        abbonamento.setDataFine(LocalDate.now().plusMonths(tipo.getDurataInMesi()));

        user.setAbbonamento(abbonamento);

        abbonamentoRepository.save(abbonamento);
    }

    @Transactional(readOnly = true)
    public Credentials getCredentials(String username) {
        return credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));
    }

}
