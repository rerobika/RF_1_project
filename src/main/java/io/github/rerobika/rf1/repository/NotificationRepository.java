package io.github.rerobika.rf1.repository;

import io.github.rerobika.rf1.domain.Notification;
import io.github.rerobika.rf1.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Notification getById (long id);
    List<Notification> getAllByPersonOrderByDate (Person person);
}
