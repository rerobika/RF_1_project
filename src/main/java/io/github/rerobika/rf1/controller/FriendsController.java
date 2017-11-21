package io.github.rerobika.rf1.controller;


import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.RelationService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class FriendsController {

    private final PersonService personService;
    private final RelationService relationService;
    private final UserService userService;

    @Autowired
    public FriendsController(PersonService personService,RelationService relationService,UserService userService)
    {
        this.relationService = relationService;
        this.personService = personService;
        this.userService = userService;
    }
    @GetMapping("/friends/{profile_id}")
    public ModelAndView getFriends(@PathVariable long profile_id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Person profilePerson = personService.getPerson(userService.getUser(profile_id));
        modelAndView.addObject("friends",personService.getFriends(profilePerson));
        modelAndView.setViewName("app.friends");
        return modelAndView;
    }
}
