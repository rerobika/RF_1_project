package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    Job findByName (String name);
}
