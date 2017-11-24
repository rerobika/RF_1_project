package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.School;
import io.github.rerobika.rf1.repository.SchoolRepository;
import io.github.rerobika.rf1.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    @Override
    public List<School> getAll() {
        return null;
    }

    @Override
    public void addSchool(School school) {
        schoolRepository.save(school);
    }

    @Override
    public void removeSchool(School school) {

    }

    @Override
    public School getSchool(long id) {
        return null;
    }

    @Override
    public School getSchoolByName(String name) {
        return schoolRepository.findByName(name);
    }
}
