package com.backend.AcaTech.Dto.Schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto {
    private Long sch_id;
    private Long user_id;
    private String start_date;
    private String end_date;
    private String sch_title;
    private String sch_content;
    private boolean sch_edu;
    private boolean sch_cons;

    public ScheduleDto(Long sch_id, Long user_id, String start_date, String end_date,
                       String sch_title, String sch_content, boolean sch_edu, boolean sch_cons) {
        this.sch_id = sch_id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.sch_title = sch_title;
        this.sch_content = sch_content;
        this.sch_edu = sch_edu;
        this.sch_cons = sch_cons;
    }
}