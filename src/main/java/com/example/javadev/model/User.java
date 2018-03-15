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

    private String name;

    private String surname;

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
        this.name = name;
        this.surname = surname;
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
