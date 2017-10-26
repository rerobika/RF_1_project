package io.github.rerobika.rf1.domain;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Relation {


    @GeneratedValue
    @Id
    private long id;

    @ManyToOne
    private Person from;
    @ManyToOne
    private Person to;

    private Date date;

    private RelationState state;

    public Relation(Person from, Person to, Date date, RelationState state) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RelationState getState() {
        return state;
    }

    public void setState(RelationState state) {
        this.state = state;
    }
}

;
