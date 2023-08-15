package com.example.heeyoon_hw_2.Dto;


import lombok.Getter;

@Getter
public class newConsultingDto {

    private Long id;
    private String st_name;
    private String st_school;
    private String st_subject;

    public newConsultingDto(Long id, String st_name, String st_school, String st_subject) {
        this.id = id;
        this.st_name = st_name;
        this.st_school = st_school;
        this.st_subject = st_subject;
    }
}
