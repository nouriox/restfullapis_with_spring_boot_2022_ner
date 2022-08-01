package ma.awb.api.services;

import lombok.RequiredArgsConstructor;
import ma.awb.api.controllers.PersonController;
import ma.awb.api.exceptions.ObjectNotFoundException;
import ma.awb.api.exceptions.RequiredObjectIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import ma.awb.api.persistence.model.Person;
import ma.awb.api.persistence.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);


    @Override
    public List<Person> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        persons
                .forEach(p -> p.add(WebMvcLinkBuilder.linkTo(methodOn(PersonController.class)
                        .listAllPersonsById(p.getPerson_id()))
                        .withSelfRel()));
        logger.info("List all Persons {} ", persons);
        return persons;
    }


    @Override
    public Person findById(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Person With Id " + id + " was not found in list"));

        person.add(linkTo(methodOn(PersonController.class).listAllPersonsById(id)).withSelfRel());
        logger.info(" found the person with id {}", id);
        return person;

    }

    @Override
    public Person createNewPerson(Person person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info(" new Person created with success {}", person);
        person.add(linkTo(methodOn(PersonController.class)
                .listAllPersonsById(person.getPerson_id()))
                .withSelfRel());

        return personRepository.save(person);


    }

    @Override
    public Person updatePerson(Person person, Integer id) {
        if (person == null) throw new RequiredObjectIsNullException();

        if (!personRepository.existsById(id)) {
            logger.error("The Person with id {} ", id + " doesn't exist");
            throw new ObjectNotFoundException("Person With Id " + id + " was not found in list");

        }

        Person saved = personRepository.save(
                new Person(
                        id,
                        person.getFirstName(),
                        person.getLastName(),
                        person.getEmail(),
                        person.getAddress(),
                        person.getGender())
        );
        logger.info("Person Updated With Success");
        return saved;


    }

    @Override
    public void deletePerson(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(personRepository::delete);
        logger.info("Person with Id {}", id + " deleted with success");
    }
}