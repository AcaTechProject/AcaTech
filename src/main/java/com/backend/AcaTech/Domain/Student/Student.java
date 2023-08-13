package com.backend.AcaTech.Domain.Student;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private Long id;

    @Column(name = "st_name", nullable = false)
    private String name;

    @Column(name = "st_gender", nullable = false)
    private String gender;

    @Column(name = "st_birth", nullable = false)
    private String birth;

    @Column(name = "st_school", nullable = false)
    private String school;

    @Column(name = "st_grade", nullable = false)
    private String grade;

    @Column(name = "st_phone", nullable = false)
    private String phone;

    @Column(name = "st_etc", nullable = false)
    private String etc;

    @Column(name = "st_image")
    private String image;

    @Column(name = "st_teacher", nullable = false)
    private String teacher;

    @Column(name = "pa_phone", nullable = false)
    private String parentPhone;

    @Column(name = "st_writer", nullable = false)
    private String st_write;

    @Column(name = "st_update_writer", nullable = false)
    private String st_update_write;

    @Column(name = "first_date", nullable = true)
    private Date first_date;

    @Column(name = "update_date", nullable = true)
    private Date update_date;


    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<StudentClass> classes = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<StudentFamily> family = new ArrayList<>();

    @Builder
    public Student(String name, String gender, String birth, String school, String grade, String phone, String etc, String image, String teacher, String parentPhone, String st_write, String st_update_write, Date first_date, Date update_date) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.school = school;
        this.grade = grade;
        this.phone = phone;
        this.etc = etc;
        this.image = image;
        this.teacher = teacher;
        this.parentPhone = parentPhone;
        this.st_write = st_write;;
        this.st_update_write = st_update_write;
        this.first_date = first_date;
        this.update_date = update_date;
    }


}
