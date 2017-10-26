package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Membership;

import java.util.List;

public interface MembershipService {
    public List<Membership> getAll();
    public void addMembership(Membership membership);
    public void removeMembership(Membership membership);
    public  Membership getMembership(long id);
}
