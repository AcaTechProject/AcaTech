package com.backend.AcaTech.Domain.Schedule;

import com.backend.AcaTech.Domain.Class.User;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "sch_title",length = 500)
    private String schTitle;

    @Column(name = "sch_content", nullable = true, length = 1000)
    private String schContent;

    @Column(name = "sch_edu")
    private boolean schEdu;

    @Column(name = "sch_cons")
    private boolean schCons;

    @Builder
    public Schedule(User user, LocalDateTime startDate, LocalDateTime endDate, String schTitle, String schContent, boolean schEdu, boolean schCons) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schTitle = schTitle;
        this.schContent = schContent != null ? schContent : "";
        this.schEdu = schEdu;
        this.schCons = schCons;
    }
}
