package com.backend.AcaTech.Domain.Schedule;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sch_id", nullable = false)
    private Long schId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "sch_title", nullable = false, length = 500)
    private String schTitle;

    @Column(name = "sch_content", nullable = false, length = 1000)
    private String schContent;

    @Column(name = "sch_edu", nullable = false)
    private boolean schEdu;

    @Column(name = "sch_cons", nullable = false)
    private boolean schCons;

    @Builder
    public Schedule(Date startDate, Date endDate, String schTitle, String schContent, boolean schEdu, boolean schCons) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.schTitle = schTitle;
        this.schContent = schContent;
        this.schEdu = schEdu;
        this.schCons = schCons;
    }
}