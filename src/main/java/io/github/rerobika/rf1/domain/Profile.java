package io.github.rerobika.rf1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Entity
public class Profile {

    @Id
    @GeneratedValue
    private long id;

    private Date birth;

    private String job;

    private String school;

    private String hobby;

    public Profile(Date birth, String job, String school, String hobby) {
        this.birth = birth;
        this.job = job;
        this.school = school;
        this.hobby = hobby;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
