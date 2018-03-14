package com.example.javadev.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lecture_id;

    private String lecture_topic;
    private String lecture_place;
    private Date lecture_date;

    @ManyToMany(mappedBy = "lectures")
    private Set<User> users = new HashSet<>();

    public Lecture(){};

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getLecture_topic() {
        return lecture_topic;
    }

    public void setLecture_topic(String lecture_topic) {
        this.lecture_topic = lecture_topic;
    }

    public String getLecture_place() {
        return lecture_place;
    }

    public void setLecture_place(String lecture_place) {
        this.lecture_place = lecture_place;
    }

    public Date getLecture_date() {
        return lecture_date;
    }

    public void setLecture_date(Date lecture_date) {
        this.lecture_date = lecture_date;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
