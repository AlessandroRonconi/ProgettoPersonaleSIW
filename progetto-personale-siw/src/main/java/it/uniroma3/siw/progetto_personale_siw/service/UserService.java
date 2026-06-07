package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.User;
import it.uniroma3.siw.progetto_personale_siw.repository.UserRepository;


@Service
@Transactional
public class UserService {

    private  UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>)this.userRepository.findAll();
    }


}
