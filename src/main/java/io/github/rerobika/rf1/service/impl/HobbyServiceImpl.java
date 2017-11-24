package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Hobby;
import io.github.rerobika.rf1.repository.HobbyRepository;
import io.github.rerobika.rf1.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyServiceImpl implements HobbyService {
    @Autowired
    HobbyRepository hobbyRepository;
    @Override
    public List<Hobby> getAll() {
        return null;
    }

    @Override
    public void addHobby(Hobby hobby) {
        hobbyRepository.save(hobby);
    }

    @Override
    public void removeHobby(Hobby hobby) {

    }

    @Override
    public Hobby getHobby(long id) {
        return null;
    }

    @Override
    public Hobby getHobbyByName(String name) {
        return hobbyRepository.findByName(name);
    }
}
