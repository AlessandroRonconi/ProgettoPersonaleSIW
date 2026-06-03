package it.uniroma3.siw.progetto_personale_siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.progetto_personale_siw.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public boolean existsByEmail(String email);

    

}