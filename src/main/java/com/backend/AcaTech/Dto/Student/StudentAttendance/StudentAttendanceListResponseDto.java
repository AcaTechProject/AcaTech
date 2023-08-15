package com.backend.AcaTech.Dto.Student.StudentAttendance;


import com.backend.AcaTech.Domain.Student.StudentAttendance;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudentAttendanceListResponseDto {

    private Long studentId;

    private Long att_id;
    private String att_result;
    private String att_reason;
    private LocalDate att_date;


    public StudentAttendanceListResponseDto(StudentAttendance entity) {
        this.studentId = entity.getStudent().getId();
        this.att_id = entity.getId();
        this.att_result = entity.getAtt_result();
        this.att_date = entity.getAtt_date();
        this.att_reason = entity.getAtt_reason();
    }

}
