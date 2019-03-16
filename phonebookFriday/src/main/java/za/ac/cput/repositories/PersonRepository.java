package za.ac.cput.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
