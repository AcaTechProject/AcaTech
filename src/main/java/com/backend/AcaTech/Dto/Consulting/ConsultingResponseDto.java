package com.backend.AcaTech.Dto.Consulting;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ConsultingResponseDto {

    private Long con_id;
    private Long studentId;
    private LocalDate con_date;
    private String con_class;
    private String con_content;
    private String con_who;
    private String con_teacher;

    public ConsultingResponseDto(Consulting entity) {
        this.con_id = entity.getId();
        this.studentId = entity.getStudent().getId();
        this.con_date = entity.getCon_date();
        this.con_content = entity.getCon_content();
        this.con_class = entity.getCon_class();
        this.con_who = entity.getCon_who();
        this.con_teacher = entity.getCon_teacher();

    }

}
