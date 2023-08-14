package com.backend.AcaTech.Dto.Score;


import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Student.StudentResponseDto;
import com.backend.AcaTech.Repository.Score.StudentScroeRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScoreListResponseDto {

    private Long st_id;
    private String sco_season;
    private String sco_test;
    private String writer;
    private LocalDate date;
    private List<ScoreInfo> scoreInfos;

    public ScoreListResponseDto(StudentScore entity) {
        this.st_id = entity.getStudent().getId();
        this.sco_season = entity.getSco_season();
        this.sco_test = entity.getSco_test();
        this.writer = entity.getWriter();
        this.date = LocalDate.from(entity.getScore_date());

        List<ScoreInfo> scoreInfoList = entity.getScoreInfos().stream()
                .map(score -> new ScoreInfo(score.getClass_name(), score.getClass_score()))
                .collect(Collectors.toList());
        this.scoreInfos = scoreInfoList;
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
