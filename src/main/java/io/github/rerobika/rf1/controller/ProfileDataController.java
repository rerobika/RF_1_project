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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
        modelAndView.addObject("posts",postService.getPostByUser(profileUser));
        modelAndView.addObject("comments",postService.getComments(postService.getPostByUser(profileUser)));
        return modelAndView;
    }

    @PostMapping(value = "/profile/{id}",params = "sendmypost")
    ModelAndView sendMyPost(@PathVariable long id, ModelAndView modelAndView, @ModelAttribute(value="postInfo") @Valid Post postInfo,
                            @RequestParam(value = "file", required = false) MultipartFile file, BindingResult result)
    {
        if(!result.hasErrors())
        {
            postInfo.setDate(new Date());
            if (file == null)
            {
                postService.addPost(postInfo);
            }
            else
            {
                //Save the uploaded file to this folder
                String UPLOADED_FOLDER = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "img";
                String fileName = new String(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date()) + file.getOriginalFilename());

                postInfo.setText("&" + UPLOADED_FOLDER + File.separator + fileName + " "+ postInfo.getText());   //TODO: insert this picture to default album

                InputStream inputStream = null;
                OutputStream outputStream = null;

                File newFile = new File(UPLOADED_FOLDER + File.separator + fileName);
                try {
                    inputStream = file.getInputStream();

                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    outputStream = new FileOutputStream(newFile);
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                postService.addPost(postInfo);
            }

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
