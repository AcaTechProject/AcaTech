package com.example.heeyoon_hw_2.Dto;
import java.util.*;
public class ClassResponseDto {
    private String subject;
    private List<StudentDto> user_class;

    public ClassResponseDto(String subject, List<StudentDto> user_class) {
        this.subject = subject;
        this.user_class = user_class;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<StudentDto> getUser_class() {
        return user_class;
    }

    public void setUser_class(List<StudentDto> user_class) {
        this.user_class = user_class;
    }
}
