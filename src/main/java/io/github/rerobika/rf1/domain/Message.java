package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @GeneratedValue
    @Id
    private long id;
    private String text;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    private Date date;

    public Message(String text, User from, User to, Date date) {
        this.text = text;
        this.from = from;
        this.to = to;
        this.date = date;
    }

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

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
