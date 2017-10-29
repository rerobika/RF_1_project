package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.domain.VerificationToken;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nandor Magyar on 10/28/17.
 */
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
