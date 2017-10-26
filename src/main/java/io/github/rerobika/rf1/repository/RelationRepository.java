package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends CrudRepository<Relation, Long> {
}
