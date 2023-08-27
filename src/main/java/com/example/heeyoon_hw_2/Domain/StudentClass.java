package com.example.heeyoon_hw_2.Domain;


import jakarta.persistence.*;

@Entity
@Table(name="student_class")
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private CourseInfo course;

    @ManyToOne
    @JoinColumn(name = "st_id")
    private Student student;

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    //class_name 칼럼을 위한 필드
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public StudentClass() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentClass(Long id, Student student) {
        this.id = id;
        this.student = student;
    }

    public StudentClass(Long id, CourseInfo course, Student student) {
        this.id = id;
        this.course = course;
        this.student = student;
    }
}
