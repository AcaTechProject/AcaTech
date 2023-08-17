package com.backend.AcaTech.Dto.Student;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import lombok.Getter;

import java.util.List;

// 메시지 전송을 위한 정보만 가져옴
@Getter
public class StudentMessageResponseDto {
    private String name;
    private String parentPhone;

    public StudentMessageResponseDto(Student entity) {
        this.name = entity.getName();
        this.parentPhone = entity.getParentPhone();

    }
}
