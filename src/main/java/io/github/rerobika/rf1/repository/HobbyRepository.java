package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Hobby;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends CrudRepository<Hobby, Long> {
}
