package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Club;

import java.util.List;

public interface ClubService {
    public List<Club> getAll();
    public void addClub(Club club);
    public void removeClub(Club club);
    public  Club getClub(long id);
}
