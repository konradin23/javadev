package com.example.javadev.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lecture")
public class Lecture{

    @Id
    @Column(name = "lecture_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lectureId;

    @Column(name = "lecture_topic")
    private String lectureTopic;

    @Column(name = "lecture_place")
    private String lecturePlace;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lecture_date")
    private Date lectureDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lectures")
    private Set<User> users = new HashSet<>();

    public Lecture() {
    }

    ;

    public Lecture(String lectureTopic, String lecturePlace, Date lectureDate) {
        this.lectureTopic = lectureTopic;
        this.lecturePlace = lecturePlace;
        this.lectureDate = lectureDate;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureTopic() {
        return lectureTopic;
    }

    public void setLectureTopic(String lectureTopic) {
        this.lectureTopic = lectureTopic;
    }

    public String getLecturePlace() {
        return lecturePlace;
    }

    public void setLecturePlace(String lecturePlace) {
        this.lecturePlace = lecturePlace;
    }

    public Date getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }



}
