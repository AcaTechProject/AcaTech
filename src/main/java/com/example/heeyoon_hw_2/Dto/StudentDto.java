package com.example.heeyoon_hw_2.Dto;

public class StudentDto {
    public StudentDto(String st_name, String class_name) {
        this.st_name = st_name;
        this.class_name = class_name;
    }

    public StudentDto() {
    }

    private String st_name;
    private String class_name;

    public String getSt_name(String st_name) {
        return this.st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
