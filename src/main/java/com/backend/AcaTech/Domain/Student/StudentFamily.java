package com.backend.AcaTech.Domain.Student;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "student_family")
@Setter
public class StudentFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fa_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Student.class)
    @JoinColumn(name = "st_id", updatable = false)
    private Student student;

    @Column(nullable = false)
    private String fa_name;

    @Column(nullable = true)
    private String fa_memo;

    @Builder
    public StudentFamily(Student student,  String fa_name, String fa_memo) {
        this.student = student;
        this.fa_name = fa_name;
        this.fa_memo = fa_memo;
    }
}
