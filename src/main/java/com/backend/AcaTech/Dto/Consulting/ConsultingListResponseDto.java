package com.backend.AcaTech.Dto.Consulting;


import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ConsultingListResponseDto {

    private Long no;
    private Long con_id;

    private Long studentId;
    private LocalDate con_date;
    private String con_class;
    private String con_teacher;


    public ConsultingListResponseDto(Consulting entity) {
        this.con_id = entity.getId();
        this.studentId = entity.getStudent().getId();
        this.con_date = entity.getCon_date();
        this.con_class = entity.getCon_class();
        this.con_teacher = entity.getCon_teacher();
    }

}
