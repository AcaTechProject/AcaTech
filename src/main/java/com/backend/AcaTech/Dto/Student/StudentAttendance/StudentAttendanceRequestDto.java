package com.backend.AcaTech.Dto.Student.StudentAttendance;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate attDate = LocalDate.now();
    private String attResult;


}