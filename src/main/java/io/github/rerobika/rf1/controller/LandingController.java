package io.github.rerobika.rf1.controller;

import com.sun.java.swing.plaf.motif.resources.motif_de;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Post;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.PostService;
import io.github.rerobika.rf1.service.UserService;
import org.apache.tomcat.jni.Time;
import org.atteo.evo.inflector.English;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.util.resources.LocaleData;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Controller
public class LandingController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private PersonService personService;

    @GetMapping("/")
    ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("app.about");
        return modelAndView;
    }

    @GetMapping("/home")
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(username);
        Person person = personService.getPerson(user);
        Post post = new Post();
        modelAndView.addObject("person", person);
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

    @RequestMapping("/profile")
    String profile() {
        return "app.profile";
    }

    @RequestMapping("/members")
    String members() {
        return "app.members";
    }
}
