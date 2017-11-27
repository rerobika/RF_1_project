package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.*;
import io.github.rerobika.rf1.repository.PersonRepository;
import io.github.rerobika.rf1.repository.UserRepository;
import io.github.rerobika.rf1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProfileDataController {

    @Autowired
    PersonService personService;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    PictureService pictureService;
    @Autowired
    AlbumService albumService;
    @Autowired
    RelationService relationService;
    @Autowired
    SchoolService schoolService;
    @Autowired
    HobbyService hobbyService;
    @Autowired
    JobService jobService;
    @Autowired
    LocationService locationService;

    private boolean passwordUpdateFail = false;



    @GetMapping("/profile/{profile_id}")
    ModelAndView profile(@PathVariable long profile_id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("app.profile");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);
        User profileUser;
        Person profilePerson;

        Person currentPerson = personService.getPerson(currentUser);
        profileUser = userService.getUser(profile_id);
        if (profileUser != null)
        {
            profilePerson = personService.getPerson(profileUser);
        }
        else
        {
                modelAndView.setViewName("redirect:/error");
                return modelAndView;
        }
        List<Post> posts = postService.getPostToUser(profileUser);
        List<Person> posted_from = new LinkedList<Person>();
        for (Post p : posts) {
            posted_from.add(personService.getPerson(p.getFrom()));
        }

        List<Post> comments = postService.getComments(postService.getPostByUser(profileUser));
        List<Person> commented_from = new LinkedList<Person>();
        for (Post c : comments) {
            commented_from.add(personService.getPerson(c.getFrom()));
        }
        List<Relation> relations = relationService.getRelations(profilePerson);
        Relation relation = new Relation();
        int pending = -1;
        if (currentPerson != profilePerson){

            for (Relation r: relations) {
                if ((r.getFrom() == currentPerson && r.getTo() == profilePerson) || (r.getFrom() == profilePerson && r.getTo() == currentPerson)){
                    relation = r;
                    break;
                }
            }

            if (relation.getFrom() != null){
                pending = relation.getState() == RelationState.marked ? 0 : 1;
            }

        }


        Post post = new Post();
        modelAndView.addObject("currentPerson", currentPerson);
        modelAndView.addObject("profilePerson", profilePerson);
        modelAndView.addObject("postInfo", post);
        modelAndView.addObject("posts", posts);
        modelAndView.addObject("posted_from", posted_from);
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("commented_from",commented_from);
        modelAndView.addObject("friends",personService.getFriends(profilePerson));
        modelAndView.addObject("relation",relation);
        modelAndView.addObject("relation_status",pending);
        return modelAndView;
    }
    @PostMapping(value = "/profile/{profile_id}",params = "sendmypost")
    ModelAndView sendMyPost(@PathVariable long profile_id, ModelAndView modelAndView, @ModelAttribute(value="postInfo") @Valid Post postInfo,
                            @RequestParam(value = "file", required = false) MultipartFile file, BindingResult result)
    {
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
                String fileName = new String(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date()) + "_"+  file.getOriginalFilename());

                postInfo.setText("&/img/post_picture/" + fileName + " "+ postInfo.getText());

                Picture post_pic = new Picture("/img/post_picture/" + fileName, albumService.getNamedAlbum(userService.getUser(profile_id), albumService.POST_ALBUM));

                pictureService.addPicture(post_pic);

                upload_file(file,UPLOADED_FOLDER + File.separator + fileName);

                postService.addPost(postInfo);
            }

        }
        modelAndView.setViewName("redirect:/profile/"+profile_id);
        return modelAndView;
    }

    @PostMapping(value = "/profile/{profile_id}",params = "mark")
    ModelAndView mark_friend(@PathVariable long profile_id, ModelAndView modelAndView)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);
        User profileUser;
        Person profilePerson;
        Person currentPerson = personService.getPerson(currentUser);
        profileUser = userService.getUser(profile_id);
        if (profileUser != null)
        {
            profilePerson = personService.getPerson(profileUser);
        }
        else
        {
            modelAndView.setViewName("redirect:/error");
            return modelAndView;
        }

        relationService.addRelation(new Relation(currentPerson, profilePerson, new Date(), RelationState.marked ));
        modelAndView.setViewName("redirect:/profile/"+profile_id);
        return modelAndView;
    }

    @PostMapping(value = "/profile/{profile_id}",params = "confirm")
    ModelAndView confirm_friend(@PathVariable long profile_id, ModelAndView modelAndView)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);
        User profileUser;
        Person profilePerson;
        Person currentPerson = personService.getPerson(currentUser);
        profileUser = userService.getUser(profile_id);
        if (profileUser != null)
        {
            profilePerson = personService.getPerson(profileUser);
        }
        else
        {
            modelAndView.setViewName("redirect:/error");
            return modelAndView;
        }
        Relation relation = relationService.getRelationFromTo(profilePerson, currentPerson);
        relation.setState(RelationState.friend);
        relationService.addRelation(relation);
        modelAndView.setViewName("redirect:/profile/"+profile_id);
        return modelAndView;
    }

    @GetMapping("/profile")
    String toCurrentProfile()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByEmail(username);

        return "redirect:/profile/"+ currentUser.getId();
    }

    @GetMapping("/profile/{profile_id}/edit")
    ModelAndView edit_profile(@PathVariable long profile_id) {
        Person profilePerson = personService.getPerson(userService.getUser(profile_id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("app.edit");
        modelAndView.addObject("profilePerson", profilePerson);
        modelAndView.addObject("passwordUpdateFail", passwordUpdateFail);
        passwordUpdateFail = false;
        return modelAndView;
    }

    @PostMapping(value = "/profile/{profile_id}/edit")
    ModelAndView updateProfile(@PathVariable long profile_id, ModelAndView modelAndView,
                               @RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "date", required = false) String birth,
                               @RequestParam(value = "school", required = false) String school,
                               @RequestParam(value = "job", required = false) String job,
                               @RequestParam(value = "location", required = false) String location,
                               @RequestParam(value = "hobby", required = false) String hobby,
                               @RequestParam(value = "plainPassword", required = false) String plainPassword,
                               @RequestParam(value = "plainPasswordOld", required = false) String plainPasswordOld,
                               BindingResult result)
    {
        Person profilePerson = personService.getPerson(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

        if(!result.hasErrors())
        {

            if (!(file == null || file.isEmpty()))
            {
                //Save the uploaded file to this folder
                String UPLOADED_FOLDER = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "webapp" +  File.separator  + "img" +  File.separator  + "profile_picture";
                String fileName = new String(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date()) + "_"+  file.getOriginalFilename());

                Picture profile_pic = new Picture("/img/profile_picture/" + fileName, albumService.getNamedAlbum(userService.getUser(profile_id), albumService.PROFILE_PICTURE_ALBUM));

                pictureService.addPicture(profile_pic);

                upload_file(file,UPLOADED_FOLDER + File.separator + fileName);

                profilePerson.setProfilePicID(profile_pic);
            }

            if (!name.isEmpty()){
                profilePerson.getUser().setName(name);
            }

            if (!school.isEmpty()){
                School new_school = new School(school);
                School existing_school = schoolService.getSchoolByName(school);
                if (existing_school != null){
                    profilePerson.setSchool(existing_school);
                }
                else{
                    profilePerson.setSchool(new_school);
                    schoolService.addSchool(new_school);
                }
            }

            if (!job.isEmpty()){
                Job new_job = new Job(job);
                Job existing_job = jobService.getJobByName(job);
                if (existing_job != null){
                    profilePerson.setJob(existing_job);
                }
                else{
                    profilePerson.setJob(new_job);
                    jobService.addJob(new_job);
                }
            }

            if (!hobby.isEmpty()){
                Hobby new_hobby = new Hobby(hobby);
                Hobby existing_hobby = hobbyService.getHobbyByName(hobby);
                if (existing_hobby != null){
                    profilePerson.setHobby(existing_hobby);
                }
                else{
                    profilePerson.setHobby(new_hobby);
                    hobbyService.addHobby(new_hobby);
                }
            }

            if (!location.isEmpty()){
                Location new_location = new Location(location);
                Location existing_location= locationService.getLocationByName(hobby);
                if (existing_location != null){
                    profilePerson.setLocation(existing_location);
                }
                else{
                    profilePerson.setLocation(new_location);
                    locationService.addLocation(new_location);
                }
            }

            if(!birth.isEmpty()){
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date birthDate = new Date();
                try {
                    birthDate = format.parse(birth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                profilePerson.setBirth(birthDate);
            }

            if (!plainPassword.isEmpty()){
                if (plainPasswordOld.equals(profilePerson.getUser().getPlainPassword()))
                {
                    profilePerson.getUser().setPlainPassword(plainPassword);
                    userService.encodePassword(profilePerson.getUser());
                }
                else
                {
                    passwordUpdateFail = true;
                }

            }

            personService.addPerson(profilePerson);

        }


        modelAndView.setViewName("redirect:/profile/" + profile_id + "/edit");
        return modelAndView;
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
