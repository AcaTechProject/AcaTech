package com.backend.AcaTech.Domain.Class;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Column(name = "user_pwd", nullable = false)
    private String password;

    @Column(name = "user_phone", nullable = false)
    private String phone;

    @Column(name = "user_major", nullable = false)
    private String major;

    @Column(name = "user_code", nullable = false)
    private String code;

    @Column(name = "user_grade")
    private String grade;

    @Column(name = "user_image")
    private String image;


    @Builder
    public User(String name, String email, String password, String phone, String major, String code, String grade, String image, String userClass) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.major = major;
        this.code = code;
        this.grade = grade;
        this.image = image;
    }
    // 생성자, Getter, Setter, 기타 메서드 생략
}
