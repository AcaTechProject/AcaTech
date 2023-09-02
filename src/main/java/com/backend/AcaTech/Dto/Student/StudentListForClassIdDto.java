package com.backend.AcaTech.Dto.Student;

import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentListForClassIdDto {


    private String name;

    @Setter
    private String className;

    //    private String school;
    private String parentPhone;
    private Long id;
//    private List<ClassInfo> classInfos;

    public StudentListForClassIdDto(Student entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.parentPhone = entity.getParentPhone();


        List<StudentListResponseDto.ClassInfo> classInfoList = entity.getClasses().stream()
                .map(studentClass -> new StudentListResponseDto.ClassInfo(studentClass.getClassName()))
                .collect(Collectors.toList());
//        this.classInfos = classInfoList;

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
