package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Club;
import io.github.rerobika.rf1.domain.Membership;
import io.github.rerobika.rf1.domain.Person;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;


public interface MembershipService {
    public List<Membership> getAll();
    public void addMembership(Membership membership);
    public void removeMembership(Membership membership);
    public  Membership getMembership(long id);
    public List<Membership> getMembersByClub(Club club);
    public boolean isMemberOfClub(Club club, Person person);
}
