package com.backend.AcaTech.Dto.Score;

import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ScoreUpdateRequestDto {

    private String sco_season;
    private String sco_test;


    private List<ScoreInfo> scoreInfos;
    public ScoreUpdateRequestDto(String sco_season, String sco_test) {
        this.sco_season = sco_season;
        this.sco_test = sco_test;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class ScoreInfo {
        private String class_name;
        private Integer class_score;
    }
}
