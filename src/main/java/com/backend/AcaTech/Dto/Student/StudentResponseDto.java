package com.backend.AcaTech.Dto.Student;

import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class StudentResponseDto {
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

    private List<StudentResponseDto.FamilyInfo> familyInfos;
    private List<StudentResponseDto.ClassInfo> classInfos;

    public StudentResponseDto(Student entity) {
        this.name = entity.getName();
        this.gender = entity.getGender();
        this.birth = entity.getBirth();
        this.school = entity.getSchool();
        this.grade = entity.getGrade();
        this.phone = entity.getPhone();
        this.etc = entity.getEtc();
        this.image = entity.getImage();
        this.teacher = entity.getTeacher();
        this.parentPhone = entity.getParentPhone();
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
