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
//    private LocalDate attDate= java.time.LocalDate.now();
    private String attResult;

    public StudentAttendanceRequestDto(Long stId, int classId, String attO, String attLate, String attX, String attEtc, String attReason, LocalDate attDate, String attResult) {
        this.stId = stId;
        this.classId = classId;
        this.attO = attO;
        this.attLate = attLate;
        this.attX = attX;
        this.attEtc = attEtc;
        this.attReason = attReason;
        this.attDate = LocalDate.now();
        this.attResult = attResult;
    }
}




