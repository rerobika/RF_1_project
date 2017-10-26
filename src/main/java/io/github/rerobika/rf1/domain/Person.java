package io.github.rerobika.rf1.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Person{
    @Id
    @GeneratedValue
    private long Id;
    private Date birth;
    @ManyToOne
    private Job job;
    @ManyToOne
    private School school;
    @ManyToOne
    private Hobby hobby;
    @ManyToOne
    private Location location;
    @OneToOne
    private User user;

    private String email;

    private String password;
    @OneToOne
    private User refID;
    @OneToOne
    private Picture profilePicID;
    @OneToMany
    List<Relation> relations;
    @OneToMany
    List<Notification> notifications;
    @OneToMany
    List<Club> clubs;

    public Person(Date birth, Job job, School school, Hobby hobby, Location location, User user, String email, String password, User refID, Picture profilePicID, List<Relation> relations, List<Notification> notifications, List<Club> clubs) {
        this.birth = birth;
        this.job = job;
        this.school = school;
        this.hobby = hobby;
        this.location = location;
        this.user = user;
        this.email = email;
        this.password = password;
        this.refID = refID;
        this.profilePicID = profilePicID;
        this.relations = relations;
        this.notifications = notifications;
        this.clubs = clubs;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getRefID() {
        return refID;
    }

    public void setRefID(User refID) {
        this.refID = refID;
    }

    public Picture getProfilePicID() {
        return profilePicID;
    }

    public void setProfilePicID(Picture profilePicID) {
        this.profilePicID = profilePicID;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
