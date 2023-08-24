package com.backend.AcaTech.Dto.Student;


import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentListResponseDto {

    private String name;

    private String school;

    private List<ClassInfo> classInfos;

    public StudentListResponseDto(Student entity) {
        this.name = entity.getName();
        this.school = entity.getSchool();

        List<ClassInfo> classInfoList = entity.getClasses().stream()
                .map(studentClass -> new ClassInfo(studentClass.getClass_name()))
                .collect(Collectors.toList());
        this.classInfos = classInfoList;

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
