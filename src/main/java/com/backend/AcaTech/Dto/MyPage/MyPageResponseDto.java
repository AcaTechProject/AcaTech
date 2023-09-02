package com.backend.AcaTech.Dto.MyPage;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponseDto {
    private String user_name;
    private String user_email;
    private String user_phone;
    private String user_major;
    private String user_grade;
    private String user_class;
    private String user_image;

    public MyPageResponseDto(User userEntity, CourseInfo classNameEntity) {
        this.user_name = userEntity.getName();
        this.user_email = userEntity.getEmail();
        this.user_phone = userEntity.getPhone();
        this.user_major = userEntity.getMajor();
        this.user_grade = userEntity.getGrade();
        this.user_class = classNameEntity.getClassName();
        this.user_image = userEntity.getImage();
    }
}