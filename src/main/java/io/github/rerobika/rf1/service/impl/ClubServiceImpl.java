package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Club;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.ClubRepository;
import io.github.rerobika.rf1.repository.UserRepository;
import io.github.rerobika.rf1.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Club> getAll()
    {
        return (List<Club>)clubRepository.findAll();
    }

    @Override
    public void addClub(Club club) {
        clubRepository.save(club);
    }

    @Override
    public void removeClub(Club club) {

    }

    @Override
    public Club getClub(long id){
        return  clubRepository.findOne(id);
    }
}
