package it.uniroma3.siw.progetto_personale_siw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.progetto_personale_siw.model.PersonalTrainer;
import it.uniroma3.siw.progetto_personale_siw.repository.PersonalTrainerRepository;

@Service
@Transactional
public class PersonalTrainerService {

    private final PersonalTrainerRepository personalTrainerRepository;

    PersonalTrainerService(PersonalTrainerRepository personalTrainerRepository) {
        this.personalTrainerRepository = personalTrainerRepository;
    }

    public List<PersonalTrainer> findAll() {
        return (List<PersonalTrainer>) this.personalTrainerRepository.findAll();
    }

}
