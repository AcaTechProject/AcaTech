package com.backend.AcaTech.Dto.Student;

import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
public class StudentCreateRequestDto {
    private String name;
    private String gender;
    private String birth;
    private String school;
    private String grade;
    private String phone;
    private String etc;
    private String image;
    private String teacher;
    private String parentPhone;
    private List<FamilyInfo> familyInfos;
    private List<ClassInfo> classInfos;
    private String st_write;
    private String st_update_write;

    private Date first_date;

    private Date update_date;

    public StudentCreateRequestDto(String name,String gender,String birth,String school,String grade,String etc,String phone, String image, String teacher, String parentPhone, String st_write, String st_update_write) {
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
        this.st_write = st_write;
        this.st_update_write = st_update_write;
        this.first_date = first_date;
        this.update_date = update_date;
    }

    public Student toEntity() {
        return new Student(name, gender, birth, school, grade, etc, phone, image, teacher, parentPhone, st_write, st_update_write, (java.sql.Date) first_date, (java.sql.Date) update_date);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class FamilyInfo {
        private String fa_name;
        private String fa_memo;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ClassInfo {
        private String class_name;
    }
}
