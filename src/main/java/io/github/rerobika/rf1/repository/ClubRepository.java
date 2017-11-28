package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Club;
import io.github.rerobika.rf1.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {
    Collection<Club> findAll();
    Collection<Club> findByOwner(Person person);

}
