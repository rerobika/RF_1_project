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
    @OneToMany
    private List<Picture> albumList;

    public Album(String name, Date date, List<Picture> album) {
        this.name = name;
        this.date = date;
        this.albumList = album;
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
