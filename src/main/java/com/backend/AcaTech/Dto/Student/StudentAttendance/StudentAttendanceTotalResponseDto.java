package com.backend.AcaTech.Dto.Student.StudentAttendance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAttendanceTotalResponseDto {
    private Long studentId;
    private long allAttendance;
    private long totalO;
    private long totalLate;
    private long totalX;
    private long totalEtc;
}
