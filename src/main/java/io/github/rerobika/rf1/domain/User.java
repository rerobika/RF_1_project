package io.github.rerobika.rf1.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "email", unique=true)
    @Email(message="{register.email.invalid}")
    @NotBlank(message="{register.email.invalid}")
    private String email;

    @Column(length=20)
    private String role;

    @Transient
    @Size(min=5, max=15, message="{register.password.size}")
    private String plainPassword;

    @Column(name = "password", length=60)
    private String password;

    @Transient
    private String repeatPassword;

    @OneToOne
    private Profile profile;

    public User() {}

    public User(String name, Profile profile) {
        this.name = name;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
