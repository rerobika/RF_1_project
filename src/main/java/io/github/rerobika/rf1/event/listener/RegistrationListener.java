package io.github.rerobika.rf1.event.listener;

import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.event.OnRegistrationCompleteEvent;
import io.github.rerobika.rf1.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Nandor Magyar on 10/28/17.
 */
@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    private final Logger logger = Logger.getLogger(RegistrationListener.class);

    private final UserService userService;

    private final JavaMailSender mailSender;

    @Autowired
    public RegistrationListener(UserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/verification/" + token;
        logger.log(Level.INFO, "http://localhost:8080" + confirmationUrl);
        String message = "Registration successful! Please confirm your e-mail with the link below. \n";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("noreply@rf1project.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
