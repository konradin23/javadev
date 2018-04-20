package com.example.javadev.model;


import javax.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue
    private int attendanceId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="lecture_id")
    private Lecture lecture;

    @Column(name = "attendance")
    private int attendance_status;

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }


    public int getAttendanceStatus() {
        return attendance_status;
    }

    public void setAttendanceStatus(int attendance_status) {
        this.attendance_status = attendance_status;
    }

    public Attendance(){

    }

    public Attendance(User user, Lecture lecture, int attendance_status) {
        this.user = user;
        this.lecture = lecture;
        this.attendance_status = attendance_status;
    }
}
