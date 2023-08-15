package com.backend.AcaTech.Dto.Score;


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

    private Long st_id;
    private String sco_season;
    private String sco_test;
    private String writer;
    private LocalDate date;
    //private List<ScoreInfo> scoreInfos;




}

