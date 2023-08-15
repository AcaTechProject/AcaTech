package com.backend.AcaTech.Dto.Score;


import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import com.backend.AcaTech.Dto.Student.StudentResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScoreResponseDto {
    private Long sc_id;
    private Long st_id;
    private String sco_season;
    private String sco_test;
    private String writer;
    private LocalDate date;
    private List<ScoreInfo> scoreInfos;

    public ScoreResponseDto(StudentScore entity, List<ScoreInfo >scoreInfos) {
        this.sc_id = entity.getId();
        this.st_id = entity.getStudent().getId();
        this.sco_season = entity.getSco_season();
        this.sco_test = entity.getSco_test();
        this.writer = entity.getWriter();
        this.date = LocalDate.from(entity.getScore_date());
        this.scoreInfos = scoreInfos;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ScoreInfo {
        private String class_name;
        private Integer class_score;

        public ScoreInfo(String class_name, Integer class_score) {
            this.class_name = class_name;
            this.class_score = class_score;
        }
    }

}

