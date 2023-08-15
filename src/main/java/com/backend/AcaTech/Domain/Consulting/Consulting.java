package com.backend.AcaTech.Domain.Consulting;


import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "student_consulting")
@Setter
public class Consulting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id", unique = true, nullable = false)
    private Long id;


    @Column(name = "con_date", nullable = false)
    private LocalDate con_date;

    @Column(name = "con_class", nullable = false)
    private String con_class;

    @Column(name = "con_content", nullable = false)
    private String con_content;

    @Column(name = "con_who", nullable = false)
    private String con_who;


    @Column(name = "con_teacher", nullable = false)
    private String con_teacher;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Student.class)
    @JoinColumn(name = "st_id")
    private Student student;

    @Builder
    public Consulting(User user, Student student, LocalDate con_date, String con_class, String con_content, String con_who, String con_teacher) {
        this.user = user;
        this.student = student;
        this.con_date = con_date;
        this.con_class = con_class;
        this.con_content = con_content;
        this.con_who = con_who;
        this.con_teacher = con_teacher;
    }

    public void update(String con_class, String con_content, String con_who) {
        this.con_class = con_class;
        this.con_content = con_content;
        this.con_who = con_who;
    }

}
