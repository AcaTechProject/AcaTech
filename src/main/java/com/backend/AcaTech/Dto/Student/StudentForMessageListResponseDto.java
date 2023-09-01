package com.backend.AcaTech.Dto.Student;

import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentForMessageListResponseDto {

    private Long id;
    private String name;

    private String parentPhone;


    public StudentForMessageListResponseDto(Student entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.parentPhone = entity.getParentPhone();

    }
}
