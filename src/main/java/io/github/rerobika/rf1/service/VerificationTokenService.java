package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.VerificationToken;

public interface VerificationTokenService {
    VerificationToken getTokenByName(String token);
    VerificationToken getTokenById(long id);
}
