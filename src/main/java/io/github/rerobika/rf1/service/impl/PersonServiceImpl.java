package io.github.rerobika.rf1.service.impl;

import com.sun.javafx.logging.JFRInputEvent;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Relation;
import io.github.rerobika.rf1.domain.RelationState;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.PersonRepository;
import io.github.rerobika.rf1.repository.RelationRepository;
import io.github.rerobika.rf1.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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
            if(rel.getFrom().equals(currentPerson) && rel.getState() == RelationState.friend) {
                friends.add(rel.getTo());
                continue;
            }
            if(rel.getTo().equals(currentPerson) && rel.getState() == RelationState.friend)
            {
                friends.add(rel.getFrom());
                continue;
            }
        }
        return friends;
    }

    @Override
    public List<Person> getNonFriends(Person currentPerson) {
        List<Person>  non_friends = new LinkedList<Person>();
        List<Person>  friends = getFriends(currentPerson);
        List<Person> allPerson = getAll();

        for(Person p : allPerson)
        {
            if (!friends.contains(p) && !p.equals(currentPerson) ){
                non_friends.add(p);
            }

        }

        List<Person>  non_friends_and_non_pending = new LinkedList<Person>();
        List<Person> pending = getPendingFromFriends(currentPerson);
        pending.addAll(getPendingToFriends(currentPerson));
        for (Person p : non_friends) {
            if (!pending.contains(p)){
                non_friends_and_non_pending.add(p);
            }
        }
        return non_friends_and_non_pending;
    }

    @Override
    public List<Person> getPendingToFriends(Person currentPerson) {
        List<Relation> friendRel = (List<Relation>)relationRepository.findByFromOrTo(currentPerson,currentPerson);
        List<Person>  friends = new ArrayList<Person>();
        for(Relation rel : friendRel)
        {
            if(rel.getTo().equals(currentPerson) && rel.getState() == RelationState.marked)
            {
                friends.add(rel.getFrom());
                continue;
            }
        }
        return friends;
    }

    @Override
    public List<Person> getPendingFromFriends(Person currentPerson) {
        List<Relation> friendRel = (List<Relation>)relationRepository.findByFromOrTo(currentPerson,currentPerson);
        List<Person>  friends = new ArrayList<Person>();
        for(Relation rel : friendRel)
        {
            if(rel.getFrom().equals(currentPerson) && rel.getState() == RelationState.marked)
            {
                friends.add(rel.getTo());
                continue;
            }
        }
        return friends;
    }
}
