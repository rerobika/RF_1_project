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
    @GetMapping("/friends")
    public ModelAndView getFriends()
    {
        ModelAndView modelAndView = new ModelAndView();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);
        Person currentPerson = personService.getPerson(currentUser);
        modelAndView.addObject("friends",personService.getFriends(currentPerson));
        modelAndView.setViewName("app.friends");
        return modelAndView;
    }
}
