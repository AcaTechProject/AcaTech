package com.backend.AcaTech.Dto.Class;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Dto.Student.StudentListResponseDto;
import lombok.Getter;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ClassStudentListResponseDto {
    private String className;
    private List<StudentInfo> enrolledStudents;
    private List<StudentListResponseDto> students;

// 0901 주석처리
//    public ClassStudentListResponseDto(StudentClass entity) {
//        this.className = entity.getClassName();
//        this.enrolledStudents = entity.getStudents().stream()
//                .map(student -> new StudentInfo(student.getName(), student.getSchool()))
//                .collect(Collectors.toList());
//    }

    public ClassStudentListResponseDto(StudentClass studentClass, List<Student> students) {
        this.className = studentClass.getClassName();
        this.students = students.stream().map(StudentListResponseDto::new).collect(Collectors.toList());
    }




    @Getter
    public static class StudentInfo {
        private String name;
        private String school;

        public StudentInfo(String name, String school) {
            this.name = name;
            this.school = school;
        }
    }
}
