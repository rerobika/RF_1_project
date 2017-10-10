package io.github.rerobika.rf1.service;

/**
 * Created by Nandor Magyar on 10/10/17.
 */
public interface MailService {
    /**
     * Sends mail to the given e-mail address
     * @param emailAddress destination address
     */
    void sendVerificationEmail(String emailAddress);
}
