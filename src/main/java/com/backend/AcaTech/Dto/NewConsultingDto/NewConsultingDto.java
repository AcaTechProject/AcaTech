package com.backend.AcaTech.Dto.NewConsultingDto;

import com.backend.AcaTech.Domain.Consulting.NewStudent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewConsultingDto {

    //넘버링
    private int num;


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
    private String st_etc;

    public NewConsultingDto() {
        // 기본 생성자
    }
    public NewConsultingDto(NewStudent entity){
        this.id = entity.getId();
        this.st_name = entity.getNew_name();
        this.st_gender = entity.getNew_gender();
        this.st_birth = entity.getNew_birth();
        this.st_school = entity.getNew_school();
        this.st_grade = entity.getNew_grade();
        this.st_phone = entity.getNew_phone();
        this.st_pa_phone = entity.getNew_pa_phone();
        this.st_class = entity.getNew_class();
        this.st_content = entity.getNew_content();
        this.st_etc = entity.getNew_etc();


    }







}