package com.example.heeyoon_hw_2.Domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="course")
public class CourseInfo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_id", unique = true, nullable = true)
    private Long classId;

    @Column(name="class_name", nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<StudentClass> students = new ArrayList<>();

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<StudentClass> getStudents() {
        return students;
    }

    public void setStudents(List<StudentClass> students) {
        this.students = students;
    }
}
