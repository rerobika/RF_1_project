package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.List;
import io.github.rerobika.rf1.domain.Person;

@Entity
public class Club{
    @Id
    @GeneratedValue
    private long Id;
    private String description;

    @ManyToOne
    private Person owner;
    @OneToOne
    private User user;
    @OneToMany
    List<Person> members;

    public Club(String description, Person owner, User user, List<Person> members) {
        this.description = description;
        this.owner = owner;
        this.user = user;
        this.members = members;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }
}

