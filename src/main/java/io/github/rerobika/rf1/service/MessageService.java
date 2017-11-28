package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Message;
import io.github.rerobika.rf1.domain.User;

import java.util.List;

public interface MessageService {
    List<Message> getByUser(User user);
    Message addMessage(Message message);
    List<Message> getConversation(User user, User partner);
}
