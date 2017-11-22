package io.github.rerobika.rf1.controller;


import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Relation;
import io.github.rerobika.rf1.domain.RelationState;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.RelationService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


@Controller
public class FriendsController {

    private final PersonService personService;
    private final RelationService relationService;
    private final UserService userService;
    private boolean success_mark;
    private boolean success_confirm;

    @Autowired
    public FriendsController(PersonService personService,RelationService relationService,UserService userService)
    {
        this.relationService = relationService;
        this.personService = personService;
        this.userService = userService;
        success_mark = false;
        success_confirm = false;
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

    @GetMapping("/members")
    public ModelAndView getFriends()
    {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("non_friends",personService.getNonFriends(profilePerson));
        modelAndView.addObject("pending_friends",personService.getPendingToFriends(profilePerson));
        modelAndView.addObject("real_friends",personService.getFriends(profilePerson));
        modelAndView.addObject("profilePerson",profilePerson);
        modelAndView.addObject("successConfirm",success_confirm);
        modelAndView.addObject("successMark",success_mark);
        success_mark = false;
        success_confirm = false;

        modelAndView.setViewName("app.members");
        return modelAndView;
    }

    @PostMapping(value = "/members",params = "mark")
    ModelAndView mark_friend(ModelAndView modelAndView, @RequestParam long id)
    {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Person currentPerson = personService.getPerson(userService.getUser(id));

        relationService.addRelation(new Relation(profilePerson, currentPerson, new Date(), RelationState.marked ));
        success_mark = true;
        modelAndView.setViewName("redirect:/members");
        return modelAndView;
    }

    @PostMapping(value = "/members",params = "confirm")
    ModelAndView confirm_friend(ModelAndView modelAndView, @RequestParam long id)
    {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Person currentPerson = personService.getPerson(userService.getUser(id));

        Relation relation = relationService.getRelationFromTo(currentPerson, profilePerson);
        relation.setState(RelationState.friend);
        relationService.addRelation(relation);
        success_confirm = true;
        modelAndView.setViewName("redirect:/members");
        return modelAndView;
    }
}
