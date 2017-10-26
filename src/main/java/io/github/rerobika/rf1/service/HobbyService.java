package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Hobby;

import java.util.List;

public interface HobbyService {
    public List<Hobby> getAll();
    public void addHobby(Hobby hobby);
    public void removeHobby(Hobby hobby);
    public  Hobby getHobby(long id);
}
