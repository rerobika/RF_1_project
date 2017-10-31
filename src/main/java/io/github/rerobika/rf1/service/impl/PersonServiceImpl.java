package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.repository.PersonRepository;
import io.github.rerobika.rf1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

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
    public Person getPerson(long id)
    {
        return personRepository.findOne(id);
    }
}
