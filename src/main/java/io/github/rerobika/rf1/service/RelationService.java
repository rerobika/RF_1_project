package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.domain.Relation;
import io.github.rerobika.rf1.domain.User;

import java.util.List;

public interface RelationService {
    public List<Relation> getAll();
    public void addRelation(Relation relation);
    public void removeRelation(Relation relation);
    public List<Relation> getRelations(Person person);
    public Relation getRelationFromTo(Person from, Person to);
}
