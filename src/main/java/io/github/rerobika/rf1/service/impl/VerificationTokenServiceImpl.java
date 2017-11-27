package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.VerificationToken;
import io.github.rerobika.rf1.repository.VerificationTokenRepository;
import io.github.rerobika.rf1.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public VerificationToken getTokenByName(String token){
        return verificationTokenRepository.findByToken(token);
    }
}
