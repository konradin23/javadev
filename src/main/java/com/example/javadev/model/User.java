package com.example.javadev.model;

import com.sun.deploy.util.ParameterUtil;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String email;

    private String password;

    private String firstname;

    private String lastname;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_lecture",
            joinColumns = {@JoinColumn(name = "email")},
            inverseJoinColumns = {@JoinColumn(name = "lecture_id")})
    private Set<Lecture> lectures = new HashSet<>();

    public void addLecture(Lecture lecture){
        lectures.add(lecture);
        lecture.getUsers().add(this);
    }

    public void removeLecture(Lecture lecture){
        lectures.remove(lecture);
        lecture.getUsers().remove(this);
    }

    public User() {
    };

    public User(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.firstname = name;
        this.lastname = surname;
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

    public String getName() {
        return firstname;
    }

    public void setName(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return lastname;
    }

    public void setSurname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }
}
