package com.backend.AcaTech.Dto.Consulting;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Student.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ConsultingUpdateRequestDto {


    private String con_who;
    private String con_class;
    private String con_content;

    @Builder
    public ConsultingUpdateRequestDto(String con_who, String con_class, String con_content) {
        this.con_who = con_who;
        this.con_class = con_class;
        this.con_content = con_content;
    }



}
