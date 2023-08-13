package com.backend.AcaTech.Domain.Score;

import com.backend.AcaTech.Domain.Student.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "student_score")
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sco_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Student.class)
    @JoinColumn(name = "st_id")
    private Student student;

    @Column(name = "sco_season", nullable = false)
    private String sco_season;


    @Column(name = "sco_test", nullable = false)
    private String sco_test;

    @Column(name = "writer", nullable = true)
    private String writer;
    @Builder
    public StudentScore(Student student, String sco_season, String sco_test, String wirter) {
        this.student = student;
        this.sco_season = sco_season;
        this.sco_test = sco_test;
        this.writer = writer;
    }

}
