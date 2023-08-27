package com.example.heeyoon_hw_2.Dto;

import java.util.*;

public class CourseWithStudentsDto {
    private String subject;  // 과목 이름
    private List<StudentDto> userClass = new ArrayList<>(); // 학생 목록

    public CourseWithStudentsDto(String subject, List<StudentDto> userClass) {
        this.subject = subject;
        this.userClass = userClass;
    }
    public CourseWithStudentsDto() {

    }
    public CourseWithStudentsDto(List<StudentDto> userClass) {
        this.userClass = userClass;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<StudentDto> getUserClass() {
        return userClass;
    }

    public void setUserClass(List<StudentDto> userClass) {
        this.userClass = userClass;
    }
}
