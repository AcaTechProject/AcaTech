package com.backend.AcaTech.Dto.Score;


import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Repository.Score.StudentScroeRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class ScoreListResponseDto {

    private Long st_id;
    private String sco_season;
    private String sco_test;
    private String writer;

    public ScoreListResponseDto(StudentScore entity) {
        this.st_id = entity.getStudent().getId(); // 이렇게 student 객체에서 name을 또 가져와야 무한 재귀에 빠지지 않음..
        this.sco_season = entity.getSco_season();
        this.sco_test = entity.getSco_test();
        this.writer = entity.getWriter();
    }
}
