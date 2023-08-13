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
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = StudentScore.class)
    @JoinColumn(name = "sco_id")
    private StudentScore studentScore;

    @Column(name = "class_name", nullable = false)
    private String class_name;

    @Column(name = "class_score", nullable = false)
    private Integer class_score;

    @Builder
    public Score(StudentScore studentScore, String class_name, Integer class_score) {
        this.studentScore = studentScore;
        this.class_name = class_name;
        this.class_score = class_score;
    }

}
