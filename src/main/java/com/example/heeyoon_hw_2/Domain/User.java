package com.example.heeyoon_hw_2.Domain;

import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String st_name;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private CourseInfo userCourse;

    public Long getUser_id() {
        return id;
    }

    public void setUser_id(Long user_id) {
        this.id = user_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public CourseInfo getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(CourseInfo userCourse) {
        this.userCourse = userCourse;
    }
}
