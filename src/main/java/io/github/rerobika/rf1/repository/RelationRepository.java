package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Relation;
import io.github.rerobika.rf1.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RelationRepository extends CrudRepository<Relation, Long> {
    Collection<Relation> findByFromOrTo(Person to, Person from);
    Relation findByFromAndTo (Person from, Person to);
}
