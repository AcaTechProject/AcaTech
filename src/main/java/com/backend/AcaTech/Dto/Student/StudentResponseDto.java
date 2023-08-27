package com.backend.AcaTech.Dto.Student;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    private String st_write;
    private String st_update_write;

    private LocalDate first_date;

    private LocalDate update_date;

    private List<StudentResponseDto.FamilyInfo> familyInfos;
    private List<StudentResponseDto.ClassInfo> classInfos;

    public StudentResponseDto(Student entity, List<StudentFamily> familyInfos, List<StudentClass> classInfos) {
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
        this.st_write = entity.getSt_write();
        this.st_update_write = entity.getSt_update_write();
        this.first_date = entity.getFirst_date();
        this.update_date = entity.getUpdate_date();

        List<FamilyInfo> familyInfoList = familyInfos.stream()
                .map(family -> new FamilyInfo(family.getFa_name(), family.getFa_memo()))
                .collect(Collectors.toList());
        this.familyInfos = familyInfoList;

        List<ClassInfo> classInfoList = classInfos.stream()
                .map(studentClass -> new ClassInfo(studentClass.getClassName()))
                .collect(Collectors.toList());
        this.classInfos = classInfoList;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FamilyInfo {
        private String fa_name;
        private String fa_memo;

        public FamilyInfo(String fa_name, String fa_memo) {
            this.fa_name = fa_name;
            this.fa_memo = fa_memo;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ClassInfo {
        private String class_name;

        public ClassInfo(String class_name) {
            this.class_name = class_name;
        }
    }

}
