package com.backend.AcaTech.Dto.Class;

// 담당수업 조회

import com.backend.AcaTech.Domain.UserClass;
import lombok.Getter;

@Getter
public class ClassListResponseDto {

    private Long user;
    private String user_name;
    private String className;
    private Long classId;

    public ClassListResponseDto(UserClass entity) {
        this.user = entity.getUser().getId();
        this.user_name = entity.getUser().getName();
        this.className = entity.getClassName().getClass_name();
        this.classId = entity.getClassName().getId();
    }

}
