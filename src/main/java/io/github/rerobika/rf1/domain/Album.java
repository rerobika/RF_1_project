package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Album {
    @GeneratedValue
    @Id
    private long id;
    private String name;
    private Date date;
    @OneToOne
    private User owner;
    @OneToMany
    private List<Picture> albumList;

    public Album (){};
    public Album(String name, Date date, List<Picture> album, User owner) {
        this.name = name;
        this.date = date;
        this.albumList = album;
        this.owner = owner;
    }

    public Album(String name, Date date, User owner) {
        this.name = name;
        this.date = date;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Picture> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Picture> albumList) {
        this.albumList = albumList;
    }
}
