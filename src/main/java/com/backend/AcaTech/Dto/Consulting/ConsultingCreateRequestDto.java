package com.backend.AcaTech.Dto.Consulting;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ConsultingCreateRequestDto {
    private User user;
    private Student student;
    private LocalDate con_date;
    private String con_class;
    private String con_content;
    private String con_who;
    private String con_teacher;

    public ConsultingCreateRequestDto(User user,  Student student, LocalDate con_date, String con_class, String con_content, String con_teacher, String con_who) {
        this.user = user;
        this.student = student;
        this.con_date = LocalDate.now();
        this.con_content = con_content;
        this.con_class = con_class;
        this.con_who = con_who;
        this.con_teacher = con_teacher;
    }

    public Consulting toEntity() {
        return new Consulting(user, student, con_date, con_class, con_content, con_teacher, con_who);
    }
}

