package com.example.heeyoon_hw_2.Domain;

import jakarta.persistence.*;

@Entity
@Table(name="student")  // DB 테이블 이름과 동일하게 설정
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id", unique = true, nullable = true)
    private Long st_id;

    // 필요한 다른 필드들 (st_name, st_gender, st_birth 등)
    @Column(name="st_name")
    private String st_name;

    @Column(name="st_gender")
    private String st_gender;

    @Column(name="st_birth")
    private String st_birth;

    @Column(name="st_school")
    private String st_school;

    @Column(name="st_grade")
    private String st_grade;

    @Column(name="st_phone")
    private String st_phone;

    @Column(name="st_etc")
    private String st_etc;

    public Student() {

    }

    // 생성자, getter, setter 등...

    public Long getSt_id() {
        return st_id;
    }

    public void setSt_id(Long st_id) {
        this.st_id = st_id;
    }

    public Student(Long st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_gender() {
        return st_gender;
    }

    public void setSt_gender(String st_gender) {
        this.st_gender = st_gender;
    }

    public String getSt_birth() {
        return st_birth;
    }

    public void setSt_birth(String st_birth) {
        this.st_birth = st_birth;
    }

    public String getSt_school() {
        return st_school;
    }

    public void setSt_school(String st_school) {
        this.st_school = st_school;
    }

    public String getSt_grade() {
        return st_grade;
    }

    public void setSt_grade(String st_grade) {
        this.st_grade = st_grade;
    }

    public String getSt_phone() {
        return st_phone;
    }

    public void setSt_phone(String st_phone) {
        this.st_phone = st_phone;
    }

    public String getSt_etc() {
        return st_etc;
    }

    public void setSt_etc(String st_etc) {
        this.st_etc = st_etc;
    }

}
