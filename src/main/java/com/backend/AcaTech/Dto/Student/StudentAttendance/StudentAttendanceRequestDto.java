package com.backend.AcaTech.Dto.Student.StudentAttendance;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class StudentAttendanceRequestDto {
    private Long stId;
    private int classId;
    private String attO;
    private String attLate;
    private String attX;
    private String attEtc;
    private String attReason;
    private LocalDate attDate;
    private String attResult;


}