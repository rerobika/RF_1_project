package io.github.rerobika.rf1.controller;

import io.github.rerobika.rf1.domain.Message;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.model.GeneralListResponse;
import io.github.rerobika.rf1.model.GeneralResponse;
import io.github.rerobika.rf1.service.MessageService;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@RestController
public class MessageController {

    private UserService userService;
    private MessageService messageService;

    @Autowired
    public MessageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> messagesForUser() {
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return messageService.getByUser(user);
    }

    @GetMapping("/message/{partnerId}")
    public GeneralListResponse<Message> messagesWithUser(@PathVariable long partnerId) {
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        User partner = userService.getUser(partnerId);
        List<Message> conversation = messageService.getConversation(user, partner);
        GeneralListResponse<Message> response = new GeneralListResponse<>();
        response.setData(conversation);
        return response;
    }

    @PostMapping("/message/{targetId}")
    public GeneralResponse submitMessage(@PathVariable long targetId, @RequestBody String message) {
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Message msg =  new Message(message, user, userService.getUser(targetId), new Date());
        Message messageSaved = messageService.addMessage(msg);
        GeneralResponse response = new GeneralResponse();
        if (messageSaved.getId() == 0) {
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
