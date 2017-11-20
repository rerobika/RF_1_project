package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.Album;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Picture;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.event.OnRegistrationCompleteEvent;
import io.github.rerobika.rf1.exception.EmailExistsException;
import io.github.rerobika.rf1.service.AlbumService;
import io.github.rerobika.rf1.service.PersonService;
import io.github.rerobika.rf1.service.PictureService;
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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
* Created by Nandor Magyar on 10/5/17.
*/
@Controller
public class AuthController {

    private final Logger logger = Logger.getLogger(AuthController.class);

    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;

    private final PersonService personService;

    private final PictureService pictureService;

    private final AlbumService albumService;

    private long default_profile_picture_id;

    @Autowired
    public AuthController(UserService userService, ApplicationEventPublisher eventPublisher, PersonService personService, PictureService pictureService, AlbumService albumService) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.personService = personService;
        this.pictureService = pictureService;
        this.albumService = albumService;
        this.default_profile_picture_id = -1;
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
        Person person = new Person();
        modelAndView.getModel().put("person", person);
        modelAndView.setViewName("app.register");
        return modelAndView;
    }

    @RequestMapping(value = "/verification/{token}", method = RequestMethod.GET)
    ModelAndView regVerify(@PathVariable String token, ModelAndView modelAndView) {
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
                          @ModelAttribute(value="person") @Valid Person person,
                          BindingResult result,
                          WebRequest request) {
        String view = "app.register";

        if (default_profile_picture_id == -1){
            Picture def_profile_picture = new Picture("/img/profile_picture/default_profile_picture.png", null);
            pictureService.addPicture(def_profile_picture);
            default_profile_picture_id = def_profile_picture.getId();
        }

        User user = person.getUser();

        if(!result.hasErrors()) {
            try {
                user = userService.register(user);

                Album profile_picture_album = new Album(albumService.PROFILE_PICTURE_ALBUM, new Date(),user);
                Album post_picture_album = new Album(albumService.POST_ALBUM, new Date(),user);


                albumService.addAlbum(profile_picture_album);
                albumService.addAlbum(post_picture_album);

                person.setProfilePicID(pictureService.getPicture(default_profile_picture_id));

                personService.addPerson(person);
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
