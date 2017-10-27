package io.github.rerobika.rf1.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    
    private String password;
    
    @OneToMany
    private List<Album> albums;
    @OneToMany
    private List<Message> messages;
    @OneToMany
    private List<Post> posts;

    @Column(name = "email", unique=true)
    @Email(message="{register.email.invalid}")
    @NotBlank(message="{register.email.invalid}")
    private String email;

    private String plainPassword;
    private String repeatPassword;
    private String role;


    public User() {}

    public User(String name, List<Album> albums, List<Message> messages, List<Post> posts) {
        this.name = name;
        this.albums = albums;
        this.messages = messages;
        this.posts = posts;
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
