package com.backend.AcaTech.Domain.Student;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "student_class")
@Setter
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Student.class)
    @JoinColumn(name = "st_id", updatable = false)
    private Student student;

    @Column(nullable = false)
    private String className;


    //@ManyToMany
    //private List<Student> students = new ArrayList<>();


    @Builder
    public StudentClass(Student student,  String class_name) {
        this.student = student;
        this.className = class_name;
    }
}
