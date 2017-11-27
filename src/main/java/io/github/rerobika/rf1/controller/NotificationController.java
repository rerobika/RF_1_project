package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.Notification;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.service.NotificationService;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    PersonService personService;
    @Autowired
    UserService userService;

    @GetMapping("/notifications")
    List<Notification> notifications() {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return notificationService.getAllByPerson(profilePerson);
    }
}
