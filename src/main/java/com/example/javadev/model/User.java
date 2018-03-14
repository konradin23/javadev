package com.example.javadev.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    private String email;

    private String password;

    private String name;

    private String surname;

    @ManyToMany
    @JoinTable(name = "users_lecture", joinColumns = {@JoinColumn(name = "email")}, inverseJoinColumns = {@JoinColumn(name = "lecture_id")})

    private Set<Lecture> lectures = new HashSet<>();

    public User() {
    }

    ;

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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }
}
