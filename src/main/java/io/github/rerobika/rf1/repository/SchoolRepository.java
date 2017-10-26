package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long> {
}
