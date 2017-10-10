package io.github.rerobika.rf1.repository;

import org.springframework.data.repository.CrudRepository;
import io.github.rerobika.rf1.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
