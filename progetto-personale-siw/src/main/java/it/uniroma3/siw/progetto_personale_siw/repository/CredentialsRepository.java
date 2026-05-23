package it.uniroma3.siw.progetto_personale_siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.util.Credentials;


@Repository
public interface CredentialsRepository extends CrudRepository<Credentials,Long>{

}
