package com.backend.AcaTech.Domain.Consulting;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "new_consulting")
@NoArgsConstructor
public class NewStudent {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "new_id", unique = true, nullable = true)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @Column(name = "new_name", nullable = false)
    private String new_name;


    @Column(name = "new_gender", nullable = false)
    private String new_gender;

    @Column(name = "new_birth", nullable = false)
    private String new_birth;

    @Column(name = "new_school", nullable = false)
    private String new_school;

    @Column(name = "new_grade", nullable = false)
    private String new_grade;

    @Column(name = "new_phone", nullable = false)
    private String new_phone;

    @Column(name = "new_pa_phone", nullable = false)
    private String new_pa_phone;

    @Column(name = "new_class", nullable = false)
    private String new_class;

    @Column(name = "new_content", nullable = false)
    private String new_content;

    @Column(name="new_etc",nullable = false)
    private String new_etc;

    @Builder
    public NewStudent(String new_name, String new_gender, String new_birth, String new_school, String new_grade, String new_phone, String new_pa_phone, String new_class, String new_content, String new_etc) {
        this.new_name = new_name;
        this.new_gender = new_gender;
        this.new_birth = new_birth;
        this.new_school = new_school;
        this.new_grade = new_grade;
        this.new_phone = new_phone;
        this.new_pa_phone = new_pa_phone;
        this.new_class = new_class;
        this.new_content = new_content;
        this.new_etc = new_etc;
    }

    @Builder
    public NewStudent(String new_name, String new_school, String new_class) {
        this.new_name = new_name;
        this.new_school = new_school;
        this.new_class = new_class;
    }


}