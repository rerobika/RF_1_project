package io.github.rerobika.rf1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Controller
public class LandingController {

    @GetMapping("/")
    ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("app.about");
        return modelAndView;
    }

    @RequestMapping("/about")
    String about() {
        return "app.about";
    }

    @RequestMapping("/home")
    String home() {
        return "app.home";
    }

    @RequestMapping("/profile")
    String profile() {
        return "app.profile";
    }

    @RequestMapping("/members")
    String members() {
        return "app.members";
    }
}
