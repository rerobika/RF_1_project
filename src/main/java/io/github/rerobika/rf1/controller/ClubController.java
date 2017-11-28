package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.*;
import io.github.rerobika.rf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.*;


@Controller
public class ClubController {

    private final ClubService clubService;
    private final PersonService personService;
    private final MembershipService membershipService;
    private final UserService userService;
    private final PostService postService;
    private Person currentPerson;

    @Autowired
    ClubController(ClubService clubService, PersonService personService, MembershipService membershipService, UserService userService,
                   PostService postService) {
        this.clubService = clubService;
        this.membershipService = membershipService;
        this.personService = personService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/club/{club_id}")
    ModelAndView getClub(@PathVariable long club_id) {
        currentPerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        ModelAndView modelAndView = new ModelAndView();
        Club club = clubService.getClub(club_id);
        Post post = new Post();
        modelAndView.addObject("postInfo", post);
        modelAndView.addObject("club", club);
        modelAndView.addObject("profilePerson", currentPerson);
        modelAndView.addObject("members", membershipService.getMembersByClub(club));
        modelAndView.addObject("profilePersonIsMember", membershipService.isMemberOfClub(club, currentPerson));
        modelAndView.addObject("posts", postService.getPostByUser(club.getUser()));
        modelAndView.setViewName("app.club");
        return modelAndView;
    }

    @PostMapping(value = "/club/{club_id}", params = "join")
    ModelAndView addMember(@PathVariable long club_id, ModelAndView modelAndView) {
        currentPerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Club club = clubService.getClub(club_id);
        Membership membership = new Membership();
        membership.setDate(new Date());
        membership.setIn(club);
        membership.setState(MembershipState.member);
        membership.setWho(currentPerson);
        membershipService.addMembership(membership);
        modelAndView.setViewName("redirect:/club/" + club.getId());
        return modelAndView;
    }

    @GetMapping("/createClub")
    ModelAndView createClub() {
        ModelAndView modelAndView = new ModelAndView();
        Club club = new Club();
        User user = new User();
        club.setUser(user);

        modelAndView.addObject("club", club);
        modelAndView.setViewName("app.createclub");
        return modelAndView;
    }

    @PostMapping("/createClub")
    ModelAndView createClub(ModelAndView modelAndView, @ModelAttribute(value = "club") @Valid Club club) {
        currentPerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        club.setOwner(currentPerson);
        Random rand = new Random();
        club.getUser().setEmail(rand.nextInt(10000) + "@" + rand.nextInt(100) + "hack.com");
        clubService.addClub(club);
        Membership membership = new Membership();
        membership.setWho(currentPerson);
        membership.setIn(club);
        membership.setState(MembershipState.member);
        membership.setDate(new Date());
        membershipService.addMembership(membership);
        modelAndView.setViewName("redirect:/club/" + club.getId());
        return modelAndView;
    }

    @PostMapping(value = "/club/{club_id}", params = "sendclubpost")
    ModelAndView sendMyPost(@PathVariable long club_id, ModelAndView modelAndView, @ModelAttribute(value = "postInfo") @Valid Post postInfo) {
        postInfo.setDate(new Date());
        postService.addPost(postInfo);
        modelAndView.setViewName("redirect:/club/" + club_id);
        return modelAndView;
    }

    @GetMapping("/clubs")
    ModelAndView listClubs() {
        currentPerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        ModelAndView modelAndView = new ModelAndView();
        List<Club> clubs = clubService.getAll();
        modelAndView.addObject("clubs", clubs);
        Map<Long, Boolean> isMemberOfClubs = new TreeMap<>();
        int i = 0;
        for (Club club : clubs) {
            isMemberOfClubs.put(club.getId(), membershipService.isMemberOfClub(club, currentPerson));
            i++;
        }
        List<Person> friends = personService.getFriends(currentPerson);
        modelAndView.addObject("friends", friends);
        modelAndView.addObject("profilePersonIsMember", isMemberOfClubs);
        modelAndView.addObject("profilePerson", currentPerson);
        modelAndView.setViewName("app.clubs");
        return modelAndView;
    }

    @PostMapping(value = "/clubs", params = "join")
    ModelAndView joinClub(ModelAndView modelAndView, @RequestParam("club_id") long club_id) {
        currentPerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        Club club = clubService.getClub(club_id);
        Membership membership = new Membership();
        membership.setDate(new Date());
        membership.setIn(club);
        membership.setState(MembershipState.member);
        membership.setWho(currentPerson);
        membershipService.addMembership(membership);
        modelAndView.setViewName("redirect:/clubs");
        return modelAndView;
    }
}

