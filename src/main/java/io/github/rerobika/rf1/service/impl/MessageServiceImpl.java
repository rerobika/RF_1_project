package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Message;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.MessageRepository;
import io.github.rerobika.rf1.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getByUser(User user) {
        return messageRepository.findByToIdOrderByDateDesc(user.getId());
    }



    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getConversation(User user, User partner) {
        List<Message> conversation = messageRepository.findByToIdAndFromIdOrderByDateDesc(user.getId(), partner.getId());
        List<Message> mirrorConversation = messageRepository.findByToIdAndFromIdOrderByDateDesc(partner.getId(), user.getId());
        return joinLists(conversation, mirrorConversation);
    }

    private List<Message> joinLists(List<Message> list1, List<Message>list2) {
        List<Message> list =  new ArrayList<>();
        while (list1.size() > 0 || list2.size() > 0) {
            Message m1 = null;
            Message m2 = null;
            if (list1.size()>0) {
                m1 = list1.get(0);
            }
            if (list2.size()>0) {
                m2 = list2.get(0);
            }
            if (m1==null && m2 != null) {
                list2.remove(m2);
                list.add(m2);
            } else if (m2 == null && m1 != null) {
                list1.remove(m1);
                list.add(m1);
            } else {
                if (m1.getDate().compareTo(m2.getDate()) > 0) {
                    list1.remove(m1);
                    list.add(m1);
                } else {
                    list2.remove(m2);
                    list.add(m2);
                }
            }
        }
        return list;
    }

}
