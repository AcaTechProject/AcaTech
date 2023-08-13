package com.backend.AcaTech.Dto.Score;


import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class ScoreCreateRequestDto {
    private Student student;
    private String sco_season;
    private String sco_test;

    private String writer;

    private List<ScoreInfo> scoreInfos;
    public ScoreCreateRequestDto(Student student, String sco_season, String sco_test, String writer) {
        this.student = student;
        this.sco_season = sco_season;
        this.sco_test = sco_test;
        this.writer = writer;
    }

    public StudentScore toEntity() {
        return new StudentScore(student, sco_season, sco_test, writer);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ScoreInfo {
        private String class_name;
        private Integer class_score;
    }
}
