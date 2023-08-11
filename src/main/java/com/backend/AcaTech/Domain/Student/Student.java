package com.backend.AcaTech.Domain.Student;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Builder
    public Student(String name, String gender, String birth, String school, String grade, String phone, String etc, String image, String teacher, String parentPhone) {
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
    }
}
