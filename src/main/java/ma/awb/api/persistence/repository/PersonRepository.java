package ma.awb.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.awb.api.persistence.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
