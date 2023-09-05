package com.backend.AcaTech.Dto.Class;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AttendanceUpdateRequestDto {
    private LocalDate attDate;
    private Long classId;
    private List<StudentAttendanceDto> students;

    @Getter
    @Setter
    public static class StudentAttendanceDto {
        private Long stId;
        private String att_o;
        private String att_late;
        private String att_x;
        private String att_etc;
        private String att_reason;

    }
}