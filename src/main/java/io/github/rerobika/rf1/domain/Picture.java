package io.github.rerobika.rf1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Picture {
    @GeneratedValue
    @Id
    private long id;
    private String location;
    @ManyToOne
    private Album album;

    public Picture(String location, Album album) {
        this.location = location;
        this.album = album;
    }

    public Picture (){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
