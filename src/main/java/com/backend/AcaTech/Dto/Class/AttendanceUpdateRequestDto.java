package com.backend.AcaTech.Dto.Class;

import lombok.*;

import java.util.*;
import java.time.LocalDate;

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