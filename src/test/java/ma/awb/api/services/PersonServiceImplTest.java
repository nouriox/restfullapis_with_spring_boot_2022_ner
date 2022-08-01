package ma.awb.api.services;

import ma.awb.api.exceptions.RequiredObjectIsNullException;
import ma.awb.api.mocks.MockPerson;
import ma.awb.api.persistence.model.Gender;
import ma.awb.api.persistence.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceImplTest {

    private final PersonRepository personRepository = mock(PersonRepository.class);
    private final PersonServiceImpl personService = new PersonServiceImpl(personRepository);

    private static final String EXPECTED_MESSAGE = "It is not allowed to persist a null object!";


    MockPerson person;

//    @InjectMocks
//    private PersonServiceImpl personService;

    @BeforeEach
    void setUp() {
        person = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllPersonsExistentInDatabase() {

        var person1 = person.mockEntityList();
        when(personRepository.findAll()).thenReturn(person1);

        var person = personService.findAllPersons();

        assertNotNull(person);
        assertEquals(14, person.size());

        var personOne = person.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getPerson_id());
        assertNotNull(personOne.getLinks());
        Assertions.assertEquals("Address Test1", personOne.getAddress());
        Assertions.assertEquals("mock@email.test1", personOne.getEmail());
        Assertions.assertEquals("First Name Test1", personOne.getFirstName());
        Assertions.assertEquals("Last Name Test1", personOne.getLastName());
        Assertions.assertEquals(Gender.FEMALE, personOne.getGender());

        var personFour = person.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getPerson_id());
        assertNotNull(personFour.getLinks());
        Assertions.assertEquals("Address Test4", personFour.getAddress());
        Assertions.assertEquals("mock@email.test4", personFour.getEmail());
        Assertions.assertEquals("First Name Test4", personFour.getFirstName());
        Assertions.assertEquals("Last Name Test4", personFour.getLastName());
        Assertions.assertEquals(Gender.MALE, personFour.getGender());


    }

    @Test
    void findPersonByIdAndReturnThePerson() {
        var person1 = person.mockEntity(1);
        person1.setPerson_id(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(person1));

        var person = personService.findById(1);

        assertNotNull(person);
        assertNotNull(person.getPerson_id());
        assertNotNull(person.getLinks());
        Assertions.assertEquals("Address Test1", person.getAddress());
        Assertions.assertEquals("mock@email.test1", person.getEmail());
        Assertions.assertEquals("First Name Test1", person.getFirstName());
        Assertions.assertEquals("Last Name Test1", person.getLastName());
        Assertions.assertEquals(Gender.FEMALE, person.getGender());


    }

    @Test
    void createNewPerson() {
        var person2 = person.mockEntity(1);

        person2.setPerson_id(1);

        when(personRepository.save(person2)).thenReturn(person2);

        var person = personService.createNewPerson(person2);

        assertNotNull(person);
        assertNotNull(person.getPerson_id());
        assertNotNull(person.getLinks());
        Assertions.assertEquals("Address Test1", person.getAddress());
        Assertions.assertEquals("mock@email.test1", person.getEmail());
        Assertions.assertEquals("First Name Test1", person.getFirstName());
        Assertions.assertEquals("Last Name Test1", person.getLastName());
        Assertions.assertEquals(Gender.FEMALE, person.getGender());


    }

    @Test
    void createWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    personService.createNewPerson(null);
                });
        final String ACTUAL_MESSAGE = exception.getMessage();

        assertTrue(ACTUAL_MESSAGE.contains(EXPECTED_MESSAGE));


    }


    @Test
    void deletePerson() {
        var person1 = person.mockEntity(1);
        person1.setPerson_id(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(person1));
        personService.deletePerson(1);
    }
}