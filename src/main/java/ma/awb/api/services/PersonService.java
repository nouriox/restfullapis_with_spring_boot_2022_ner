package ma.awb.api.services;


import ma.awb.api.persistence.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAllPersons();

    Person findById(Integer id);

    Person createNewPerson(Person person);

    Person updatePerson(Person person, Integer id);

    void deletePerson(Integer id);

}
