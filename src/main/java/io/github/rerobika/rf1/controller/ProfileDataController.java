package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.domain.VerificationToken;
import io.github.rerobika.rf1.repository.PersonRepository;
import io.github.rerobika.rf1.repository.UserRepository;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileDataController {

    @Autowired
    PersonService personService;
    @Autowired
    UserService userService;

    @GetMapping("/profile")
    ModelAndView profile()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("app.profile");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(username);
        Person person = personService.getPerson(user);
        modelAndView.addObject("person",person);
        return modelAndView;
    }
}
