package com.backend.AcaTech.Dto.Score;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@Getter
public class ScoreGraphListResponseDto2 {

    private String sco_season;

    private List<ScoreInfo> scoreInfos;

    public ScoreGraphListResponseDto2(String sco_season, List<ScoreInfo> scoreInfos) {
        this.sco_season = sco_season;
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

