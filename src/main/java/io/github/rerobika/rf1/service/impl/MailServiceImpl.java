package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
/**
 * Created by Nandor Magyar on 10/5/17.
 */
@Service
public class MailServiceImpl implements MailService {

    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.enabled}")
    private Boolean enable;

    private void send(MimeMessagePreparator messagePreparator) {
        if(enable) {
            mailSender.send(messagePreparator);
        }
    }

    @Autowired
    public MailServiceImpl(TemplateEngine templateEngine) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        templateEngine.setTemplateResolver(templateResolver);

        this.templateEngine = templateEngine;
    }

    public void sendVerificationEmail(String emailAddress) {

        Context context = new Context();
        context.setVariable("name", "Bob");

        String emailContents = templateEngine.process("verifyemail", context);
        //todo: process and actually send that mail
        send(null);
    }
}
