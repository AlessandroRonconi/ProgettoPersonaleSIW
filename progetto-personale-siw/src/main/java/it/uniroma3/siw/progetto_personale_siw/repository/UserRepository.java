package it.uniroma3.siw.progetto_personale_siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progetto_personale_siw.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public boolean existsByEmail(String email);

}