package com.backend.AcaTech.Dto.Student;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class StudentUpdateRequestDto {
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
    private List<StudentCreateRequestDto.FamilyInfo> familyInfos;
    private List<StudentCreateRequestDto.ClassInfo> classInfos;
    private String st_write;
    private String st_update_write;

    private LocalDate first_date;

    private LocalDate update_date;

    @Builder
    public StudentUpdateRequestDto(String name,String gender,String birth,String school,String grade,String etc,String phone, String image, String teacher, String parentPhone, String st_write, String st_update_write, LocalDate update_date) {
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
        this.update_date = LocalDate.now();
    }
}
