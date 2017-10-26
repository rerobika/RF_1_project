package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {
}
