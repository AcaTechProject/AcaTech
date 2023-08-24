package com.backend.AcaTech.Dto.Score;


import com.backend.AcaTech.Domain.Score.StudentScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScoreGraphListResponseDto1 {

    private String sco_season_test;

    private List<ScoreListResponseDto.ScoreInfo> scoreInfos;

    public ScoreGraphListResponseDto1(StudentScore entity) {
        this.sco_season_test = entity.getSco_season() + ' ' + entity.getSco_test();

        List<ScoreListResponseDto.ScoreInfo> scoreInfoList = entity.getScoreInfos().stream()
                .map(score -> new ScoreListResponseDto.ScoreInfo(score.getClass_name(), score.getClass_score()))
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
