package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Notification;
import io.github.rerobika.rf1.domain.Person;

import java.util.List;

public interface NotificationService {
    public List<Notification> getAllByPerson(Person person);
    public void addNotification(Notification notification);
    public void removeNotification(Notification notification);
    public  Notification getNotification(long id);
}
