package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Message;

import java.util.List;

public interface MessageService {
    public List<Message> getAll();
    public void addMessage(Message message);
    public void removeMessage(Message message);
    public  Message getMessage(long id);
}
