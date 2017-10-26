package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Relation;

import java.util.List;

public interface RelationService {
    public List<Relation> getAll();
    public void addRelation(Relation relation);
    public void removeRelation(Relation relation);
    public  Relation getRelation(long id);
}
