package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    public Person findByUser(User user);
}
