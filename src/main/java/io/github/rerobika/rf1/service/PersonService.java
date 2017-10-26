package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Person;

import java.util.List;

public interface PersonService {
    public List<Person> getAll();
    public void addPerson(Person person);
    public void removePerson(Person person);
    public  Person getPerson(long id);
}

