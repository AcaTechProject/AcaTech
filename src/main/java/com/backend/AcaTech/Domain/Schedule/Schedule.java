package com.backend.AcaTech.Domain.Schedule;

import com.backend.AcaTech.Domain.Class.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "sch_title", nullable = false, length = 500)
    private String schTitle;

    @Column(name = "sch_content", nullable = false, length = 1000)
    private String schContent;

    @Column(name = "sch_edu", nullable = false)
    private boolean schEdu;

    @Column(name = "sch_cons", nullable = false)
    private boolean schCons;

    @Builder
    public Schedule(User user, LocalDateTime startDate, LocalDateTime endDate, String schTitle, String schContent, boolean schEdu, boolean schCons) {
        this.user = user; // User 객체를 받아서 user 필드 초기화
        this.startDate = startDate;
        this.endDate = endDate;
        this.schTitle = schTitle;
        this.schContent = schContent;
        this.schEdu = schEdu;
        this.schCons = schCons;
    }
}