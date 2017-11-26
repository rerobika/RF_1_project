package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Club;
import io.github.rerobika.rf1.domain.Membership;
import io.github.rerobika.rf1.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {
    Collection<Membership> findByIn(Club club);
    Membership findByInAndWho(Club club,Person person);
}
