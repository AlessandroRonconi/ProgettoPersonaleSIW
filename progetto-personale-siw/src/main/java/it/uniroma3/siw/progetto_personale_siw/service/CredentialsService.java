package it.uniroma3.siw.progetto_personale_siw.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.Credentials;
import it.uniroma3.siw.progetto_personale_siw.model.DuplicateCredentialsException;
import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.CredentialsRepository;
import it.uniroma3.siw.progetto_personale_siw.repository.UserRepository;

@Service
@Transactional
public class CredentialsService {

    private final PasswordEncoder passwordEncoder;
    private CredentialsRepository credentialsRepository;
    private UserRepository userRepository;
    public CredentialsService(CredentialsRepository credentialsRepository, PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.credentialsRepository = credentialsRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public void save(Credentials credentials) {
        if (credentialsRepository.existsByUsername(credentials.getUsername())) {
            throw new DuplicateCredentialsException("Username '" + credentials.getUsername() + "' già esistente. Scegline un altro.");
        }
        User user = credentials.getUser();
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateCredentialsException("Email '" + user.getEmail() + "' già registrata. Usane un'altra.");
        }
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        credentials.setRuolo(Credentials.USER_ROLE);
        this.credentialsRepository.save(credentials);
    }

}
