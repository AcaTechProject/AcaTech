package com.example.heeyoon_hw_2.Dto;


import com.example.heeyoon_hw_2.Domain.newStudent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class newConsultingDto {

    private Long id;
    private String st_name;
    private String st_gender;
    private String st_birth;
    private String st_school;
    private String st_grade;
    private String st_phone;
    private String st_pa_phone;
    private String st_class;
    private String st_content;

    public newConsultingDto(newStudent entity){
        this.st_name = entity.getNew_name();
        this.st_gender = entity.getNew_gender();
        this.st_birth = entity.getNew_birth();
        this.st_school = entity.getNew_school();
        this.st_grade = entity.getNew_grade();
        this.st_phone = entity.getNew_phone();
        this.st_pa_phone = entity.getNew_pa_phone();
        this.st_class = entity.getNew_class();
        this.st_content = entity.getNew_content();


    }
}
