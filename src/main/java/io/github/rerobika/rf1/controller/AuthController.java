package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.service.MailService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
* Created by Nandor Magyar on 10/5/17.
*/
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/login")
    String admin() {
        return "app.login";
    }

    @GetMapping("/verifymail")
    String verifyEmail() {
        return "app.verifymail";
    }

    @GetMapping("/register")
    ModelAndView register(ModelAndView modelAndView) {

        User user = new User();

        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("app.register");
        return modelAndView;
    }

    @PostMapping(value="/register")
    ModelAndView register(ModelAndView modelAndView, @ModelAttribute(value="user") @Valid User user, BindingResult result) {
        modelAndView.setViewName("app.register");

        if(!result.hasErrors()) {
            userService.register(user);

            mailService.sendVerificationEmail(user.getEmail());

            modelAndView.setViewName("redirect:/verifymail");
        }
        return modelAndView;
    }
}
