package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.event.OnRegistrationCompleteEvent;
import io.github.rerobika.rf1.exception.EmailExistsException;
import io.github.rerobika.rf1.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
* Created by Nandor Magyar on 10/5/17.
*/
@Controller
public class AuthController {

    private final Logger logger = Logger.getLogger(AuthController.class);

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AuthController(UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/login")
    String admin() {
        return "app.login";
    }

    @GetMapping("/verifymail")
    String verifyMail() {
        return "app.verifymail";
    }

    @GetMapping("/register")
    ModelAndView register(ModelAndView modelAndView) {
        User user = new User();
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("app.register");
        return modelAndView;
    }

    @RequestMapping(value = "/verification/{token}", method = RequestMethod.GET)
    ModelAndView regVerfiy(@PathVariable String token, ModelAndView modelAndView) {
        String status = userService.validateVerificationToken(token);
        if (status.contentEquals("valid")) {
            modelAndView.setViewName("redirect:/");
            User user = userService.getUser(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject(new Error("Invalid token, " + status));
        }
        return modelAndView;
    }

    @PostMapping(value="/register")
    ModelAndView register(ModelAndView modelAndView,
                          @ModelAttribute(value="user") @Valid User user,
                          BindingResult result,
                          WebRequest request) {

        String view = "app.register";

        if(!result.hasErrors()) {
            try {
                user = userService.register(user);
            } catch (EmailExistsException e) {
                logger.error(e.getMessage(), e);
                return new ModelAndView("app.error", "error", new Error(e.getMessage()));
            }

            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (user, request.getLocale(), appUrl));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return new ModelAndView("app.error", "error", new Error("Could not send mail"));
            }
            view = "app.verifymail";
        }

        modelAndView.setViewName(view);
        return modelAndView;
    }
}
