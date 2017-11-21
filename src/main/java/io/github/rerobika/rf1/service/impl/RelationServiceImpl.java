package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Relation;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.RelationRepository;
import io.github.rerobika.rf1.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public List<Relation> getAll() {
        return null;
    }

    @Override
    public void addRelation(Relation relation) {
        relationRepository.save(relation);
    }

    @Override
    public void removeRelation(Relation relation) {

    }

    @Override
    public List<Relation> getRelations(Person person)
    {
        return (List<Relation>)relationRepository.findByFromOrTo(person,person);
    }

    @Override
    public Relation getRelationFromTo(Person from, Person to) {
        return relationRepository.findByFromAndTo(from, to);
    }

}
