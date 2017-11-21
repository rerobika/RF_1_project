package io.github.rerobika.rf1.service.impl;

import com.sun.javafx.logging.JFRInputEvent;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Relation;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.PersonRepository;
import io.github.rerobika.rf1.repository.RelationRepository;
import io.github.rerobika.rf1.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    RelationRepository relationRepository;

    @Override
    public List<Person> getAll()
    {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public void addPerson(Person person)
    {
        personRepository.save(person);
    }

    @Override
    public void removePerson(Person person)
    {
        personRepository.delete(person);
    }

    @Override
    public Person getPerson(User user)
    {
        return personRepository.findByUser(user);
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public List<Person> getFriends(Person currentPerson) {
        List<Relation> friendRel = (List<Relation>)relationRepository.findByFromOrTo(currentPerson,currentPerson);
        List<Person>  friends = new ArrayList<Person>();
        for(Relation rel : friendRel)
        {
            if(rel.getFrom().equals(currentPerson)) {
                friends.add(rel.getFrom());
                continue;
            }
            if(rel.getTo().equals(currentPerson))
            {
                friends.add(rel.getTo());
                continue;
            }
        }
        return friends;
    }
}
