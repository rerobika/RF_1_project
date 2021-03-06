package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @GeneratedValue
    @Id
    private long id;
    private String text;
    @ManyToOne
    private User from;
    @ManyToOne
    private User to;
    @OneToOne
    private Post parent;
    private Date date;



    private long likeNumber;

    public Post(){};

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

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(long likeNumber) {
        this.likeNumber = likeNumber;
    }
}
