package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.PostService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Controller
public class LandingController implements ErrorController {

    private final UserService userService;
    private final PostService postService;
    private final PersonService personService;

    private static final String ERROR_PATH = "/error";

    @Autowired
    public LandingController(UserService userService, PostService postService, PersonService personService) {
        this.userService = userService;
        this.postService = postService;
        this.personService = personService;
    }

    @GetMapping("/")
    ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("app.about");
        return modelAndView;
    }

    @GetMapping("/home")
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);
        Person currentPerson = personService.getPerson(currentUser);
        Post post = new Post();
        modelAndView.addObject("currentPerson", currentPerson);
        modelAndView.addObject("postInfo", post);
        modelAndView.addObject("posts",postService.getAll());
        modelAndView.addObject("comments",postService.getComments(postService.getAll()));
        modelAndView.setViewName("app.home");
        return modelAndView;
    }

    @PostMapping("/home")
    ModelAndView home(ModelAndView modelAndView, @ModelAttribute(value="postInfo") @Valid Post postInfo, BindingResult result)
    {
        if(!result.hasErrors())
        {
            if(!postInfo.getText().isEmpty())
            {
                postInfo.setDate(new Date());
                postService.addPost(postInfo);
            }
        }
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @RequestMapping("/about")
    String about() { return "app.about";    }

    @RequestMapping("/error")
    String error() { return "app.error"; }

    @RequestMapping("/profile")
    String profile() {
        return "app.profile";
    }

    @RequestMapping("/members")
    String members() {
        return "app.members";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
