package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Membership {
    @GeneratedValue
    @Id
    private long id;

    @ManyToOne
    private Club in;
    @ManyToOne
    private Person who;

    private Date date;

    private MembershipState state;

    public Membership(Club in, Person who, Date date, MembershipState state) {
        this.in = in;
        this.who = who;
        this.date = date;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Club getIn() {
        return in;
    }

    public void setIn(Club in) {
        this.in = in;
    }

    public Person getWho() {
        return who;
    }

    public void setWho(Person who) {
        this.who = who;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MembershipState getState() {
        return state;
    }

    public void setState(MembershipState state) {
        this.state = state;
    }
}
