package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Notification {
    @GeneratedValue
    @Id
    private long id;
    private String text;
    private Date date;
    @ManyToOne
    private Person person;

    public Notification(String text, Date date, Person person) {
        this.text = text;
        this.date = date;
        this.person = person;
    }

    public Notification (){};
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
