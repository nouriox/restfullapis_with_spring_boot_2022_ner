package ma.awb.api.configuration;

import lombok.RequiredArgsConstructor;
import ma.awb.api.persistence.model.Gender;
import ma.awb.api.persistence.repository.PersonRepository;
import ma.awb.api.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ma.awb.api.persistence.model.Person;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PersonConfig implements CommandLineRunner {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Override
    public void run(String... args) throws Exception {
        List<Person> people = List.of(
                new Person("vanilson", "wayne", "vanilson@gmail.com", "Rua Luz Soriano Porto", Gender.MALE),
                new Person("tugce", "senturk", "tugce@gmail.com", "Street Amesterdam", Gender.FEMALE),
                new Person("Rick", "Renner", "rick@gmail.com", "mexico ", Gender.OTHER),
                new Person("Rui", "Renato", "rui@gmail.com", null, Gender.MALE),
                new Person("Maria", "Madalena", "maria@gmail.com", null, Gender.FEMALE)
        );
//        logger.info("saving person .... ");
//        personRepository.saveAll(people);
//        logger.info("Person save with success {}", people);

    }
}
