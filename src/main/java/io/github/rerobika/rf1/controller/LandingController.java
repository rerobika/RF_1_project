package io.github.rerobika.rf1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Controller
public class LandingController {

    @GetMapping("/")
    public String landingPage() {
        return "index";
    }



}
