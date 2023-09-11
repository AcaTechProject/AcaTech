package com.backend.AcaTech.Dto.MyPage;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Class.CourseInfo;
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

    public MyPageResponseDto(User user, CourseInfo classNameEntity) {
        this.user_name = user.getName();
        this.user_email = user.getEmail();
        this.user_phone = user.getPhone();
        this.user_major = user.getMajor();
        this.user_grade = user.getGrade();
        if (classNameEntity != null) {
            this.user_class = classNameEntity.getClassName();
        } else {
            this.user_class = "";
        }
        this.user_image = user.getImage();
    }
}