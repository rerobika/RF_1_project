package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.*;
import io.github.rerobika.rf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Controller
public class LandingController implements ErrorController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    PersonService personService;
    @Autowired
    RelationService relationService;
    @Autowired
    PictureService pictureService;
    @Autowired
    AlbumService albumService;
    @Autowired
    NotificationService notificationService;

    private static final String ERROR_PATH = "/error";


    @GetMapping("/")
    ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("app.about");
        return modelAndView;
    }

    @GetMapping("/home")
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

        List<Post> posts = postService.getAllFriendsPost(profilePerson);
        List<Person> posted_from = new LinkedList<Person>() { };
        for (Post p : posts) {
            posted_from.add(personService.getPerson(p.getFrom()));
        }

        List<Post> comments = postService.getComments(postService.getPostByUser(profilePerson.getUser()));
        List<Person> commented_from = new LinkedList<Person>() { };
        for (Post c : comments) {
            commented_from.add(personService.getPerson(c.getFrom()));
        }

        Post post = new Post();
        modelAndView.addObject("currentPerson", profilePerson);
        modelAndView.addObject("profilePerson", profilePerson);
        modelAndView.addObject("postInfo", post);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("posted_from", posted_from);
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("commented_from",commented_from);
        modelAndView.addObject("friends",personService.getFriends(profilePerson));
        modelAndView.addObject("notification", notificationService.getAllByPerson(profilePerson));
        modelAndView.setViewName("app.home");
        return modelAndView;
    }

    @PostMapping(value = "/home",params = "sendmypost")
    ModelAndView sendMyPost(ModelAndView modelAndView, @ModelAttribute(value="postInfo") @Valid Post postInfo,
                            @RequestParam(value = "file", required = false) MultipartFile file, BindingResult result)
    {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        if(!result.hasErrors())
        {
            postInfo.setDate(new Date());
            if (file == null || file.isEmpty())
            {
                postService.addPost(postInfo);
            }
            else
            {
                //Save the uploaded file to this folder
                String UPLOADED_FOLDER = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "webapp" +  File.separator  + "img" +  File.separator  + "post_picture";
                String fileName = new String(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date()) + "_"+  file.getOriginalFilename().replaceAll("\\s",""));

                postInfo.setText("&/img/post_picture/" + fileName + " "+ postInfo.getText());

                Picture post_pic = new Picture("/img/post_picture/" + fileName, albumService.getNamedAlbum(profilePerson.getUser(), albumService.POST_ALBUM));

                pictureService.addPicture(post_pic);

                upload_file(file,UPLOADED_FOLDER + File.separator + fileName);

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

    private void upload_file(MultipartFile file, String path){
        File newFile = new File(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
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

    }
}
