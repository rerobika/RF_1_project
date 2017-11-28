package io.github.rerobika.rf1.controller;


import io.github.rerobika.rf1.domain.*;
import io.github.rerobika.rf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


@Controller
public class FriendsController {

    private final PersonService personService;
    private final RelationService relationService;
    private final UserService userService;
    private final NotificationService notificationService;
    private final ClubService clubService;
    private boolean success_mark;
    private boolean success_confirm;

    @Autowired
    public FriendsController(PersonService personService,RelationService relationService,UserService userService, NotificationService notificationService,ClubService clubService)
    {
        this.clubService = clubService;
        this.relationService = relationService;
        this.personService = personService;
        this.userService = userService;
        this.notificationService = notificationService;
        success_mark = false;
        success_confirm = false;
    }
    @GetMapping("/friends/{profile_id}")
    public ModelAndView getFriends(@PathVariable long profile_id)
    {
        ModelAndView modelAndView = new ModelAndView();
        Person currentPerson = personService.getPerson(userService.getUser(profile_id));
        modelAndView.addObject("friends",personService.getFriends(currentPerson));
        modelAndView.addObject("currentPerson", currentPerson);
        modelAndView.addObject("profilePerson", personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName())));
        modelAndView.addObject("notification", notificationService.getAllByPerson(personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()))));
        modelAndView.setViewName("app.friends");
        return modelAndView;
    }

    @GetMapping("/members")
    public ModelAndView getFriends()
    {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        ModelAndView modelAndView = new ModelAndView();
        List<Club> smallClubList =clubService.getClubByPerson(profilePerson);


        modelAndView.addObject("smallClubList",smallClubList);
        modelAndView.addObject("non_friends",personService.getNonFriends(profilePerson));
        modelAndView.addObject("pending_friends",personService.getPendingToFriends(profilePerson));
        modelAndView.addObject("real_friends",personService.getFriends(profilePerson));
        modelAndView.addObject("profilePerson",profilePerson);
        modelAndView.addObject("successConfirm",success_confirm);
        modelAndView.addObject("successMark",success_mark);
        modelAndView.addObject("notification", notificationService.getAllByPerson(profilePerson));

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
        if( currentPerson != profilePerson){
            notificationService.addNotification(new Notification("/profile/" + profilePerson.getUser().getId() + "|" + profilePerson.getUser().getName() + " marked you as friend! ", new Date(), currentPerson));
        }
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
        if( currentPerson != profilePerson){
            notificationService.addNotification(new Notification("/profile/" + profilePerson.getUser().getId() + "|" + profilePerson.getUser().getName() + " confirmed your friend request! ", new Date(), currentPerson));
        }
        modelAndView.setViewName("redirect:/members");
        return modelAndView;
    }
}
