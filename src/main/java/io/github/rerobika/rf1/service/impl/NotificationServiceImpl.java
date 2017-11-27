package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Notification;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.repository.NotificationRepository;
import io.github.rerobika.rf1.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<Notification> getAllByPerson(Person person) {
        return notificationRepository.getAllByPersonOrderByDate(person);
    }

    @Override
    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void removeNotification(Notification notification) {

    }

    @Override
    public Notification getNotification(long id) {
        return notificationRepository.getById(id);
    }
}
