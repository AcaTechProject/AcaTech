package com.backend.AcaTech.Domain.Student;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student_attendance")
public class StudentAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id")
    private Long id;

    @Column(name = "att_o", nullable = true)
    private String att_o;

    @Column(name = "att_late", nullable = true)
    private String att_late;

    @Column(name = "att_x", nullable = true)
    private String att_x;

    @Column(name = "att_etc", nullable = true)
    private String att_etc;

    @Column(name = "att_reason", nullable = true)
    private String att_reason;

    @Column(name = "att_date", nullable = true)
    private LocalDate att_date;

    @Column(name = "att_result", nullable = true)
    private String att_result;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Student.class)
    @JoinColumn(name = "st_id", updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = StudentClass.class)
    @JoinColumn(name = "class_id", updatable = false)
    private StudentClass studentClass;

    @Builder
    public StudentAttendance(String att_o, String att_late, String att_x, String att_etc, String att_reason, LocalDate att_date, String att_result, Student student,  StudentClass studentClass) {
        this.att_o = att_o;
        this.att_late = att_late;
        this.att_x = att_x;
        this.att_etc = att_etc;
        this.att_reason = att_reason;
        this.att_date = att_date;
        this.att_result = att_result;
        this.student = student;
        this.studentClass = studentClass;
    }

}
