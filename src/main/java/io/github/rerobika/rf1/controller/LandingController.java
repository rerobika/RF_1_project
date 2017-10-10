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
    ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("app.homepage");
        return modelAndView;
    }

    @RequestMapping("/about")
    String about() {
        return "app.about";
    }
}
