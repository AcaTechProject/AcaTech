package com.backend.AcaTech.Dto.Class;

import com.backend.AcaTech.Domain.Class.UserClass;
import lombok.Getter;

@Getter
public class ClassDetailResponseDto {

    private String courseName;
    // 새로 추가
    private String user_name;
    private String className;
    private Long classId;


//    private List<StudentListResponseDto.ClassInfo> classInfoList ;


    public ClassDetailResponseDto(UserClass entity) {
        this.courseName = parseCourseName(entity.getClassName().getClassName());
        // 새로 추가
        this.user_name = entity.getUser().getName();
        this.className = entity.getClassName().getClassName();
        this.classId = entity.getClassName().getId();

    }

    private String parseCourseName(String className) {
        int spaceIndex = className.indexOf(" ");
        if (spaceIndex != -1) {
            return className.substring(0, spaceIndex);
        }
        return className;  // 공백이 없는 경우 전체 문자열 반환
    }
}