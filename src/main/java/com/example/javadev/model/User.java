package com.example.javadev.model;

import com.sun.deploy.util.ParameterUtil;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Comparable<User> {
    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Transient
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Transient
    private int numberOfLecturesAttended;

    @ManyToMany//(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Attendance> attendance;

    public User() {
    };

    public User(int userId, String email, String password, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getNumberOfLecturesAttended() {
        return numberOfLecturesAttended;
    }

    public void setNumberOfLecturesAttended(int numberOfLecturesAttended) {
        this.numberOfLecturesAttended = numberOfLecturesAttended;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(Set<Attendance> attendance) {
        this.attendance = attendance;
    }

    @Override
    public int compareTo(User o) {
        int compareLastName = lastName.compareTo(o.lastName);

        if(compareLastName == 0) {
            return firstName.compareTo(o.firstName);
        }
        else {
            return compareLastName;
        }
    }
}
