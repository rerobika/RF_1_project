package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Club;
import io.github.rerobika.rf1.domain.Membership;
import io.github.rerobika.rf1.domain.Person;
import io.github.rerobika.rf1.repository.MembershipRepository;
import io.github.rerobika.rf1.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public List<Membership> getAll() {
        return null;
    }

    @Override
    public void addMembership(Membership membership)
    {
        membershipRepository.save(membership);
    }

    @Override
    public void removeMembership(Membership membership) {

    }

    @Override
    public Membership getMembership(long id) {
        return null;
    }

    @Override
    public List<Membership> getMembersByClub(Club club)
    {
        return (List<Membership>) membershipRepository.findByIn(club);
    }

    @Override
    public boolean isMemberOfClub(Club club, Person person)
    {
        if(membershipRepository.findByInAndWho(club,person)!=null)
        {
            return true;
        }
        return false;
    }

}
