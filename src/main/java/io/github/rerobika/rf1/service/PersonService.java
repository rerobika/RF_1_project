package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.User;

import java.util.List;

public interface PersonService {
    public List<Person> getAll();
    public void addPerson(Person person);
    public void removePerson(Person person);
    public  Person getPerson(User user);
    public void updatePerson (Person person);
    public List<Person> getFriends(Person currentPerson);
    public List<Person> getNonFriends(Person currentPerson);
    public List<Person> getPendingToFriends(Person currentPerson);
    public List<Person> getPendingFromFriends(Person currentPerson);
}

