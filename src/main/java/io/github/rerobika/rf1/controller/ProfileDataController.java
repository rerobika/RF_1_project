package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.domain.VerificationToken;
import io.github.rerobika.rf1.repository.PersonRepository;
import io.github.rerobika.rf1.repository.UserRepository;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.PostService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
public class ProfileDataController {

    @Autowired
    PersonService personService;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @GetMapping("/profile/{id}")
    ModelAndView profile(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("app.profile");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);
        User profileUser;
        Person profilePerson;

        Person currentPerson = personService.getPerson(currentUser);
        profileUser = userService.getUser(id);
        if (profileUser != null)
        {
            profilePerson = personService.getPerson(profileUser);
        }
        else
        {
                modelAndView.setViewName("redirect:/error");
                return modelAndView;
        }
        Post post = new Post();
        modelAndView.addObject("currentPerson", currentPerson);
        modelAndView.addObject("profilePerson", profilePerson);
        modelAndView.addObject("postInfo", post);
        return modelAndView;
    }

    @PostMapping(value = "/profile/{id}",params = "sendmypost")
    ModelAndView sendMyPost(@PathVariable long id, ModelAndView modelAndView, @ModelAttribute(value="postInfo") @Valid Post postInfo, BindingResult result)
    {
        if(!result.hasErrors())
        {
            postInfo.setDate(new Date());
            postService.addPost(postInfo);
        }
        modelAndView.setViewName("redirect:/profile/"+id);
        return modelAndView;
    }
    @GetMapping("/profile")
    String toCurrentProfile()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);

        return "redirect:/profile/"+ currentUser.getId();
    }
}
