package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.School;

import java.util.List;

public interface SchoolService {
    public List<School> getAll();
    public void addSchool(School school);
    public void removeSchool(School school);
    public  School getSchool(long id);

}
