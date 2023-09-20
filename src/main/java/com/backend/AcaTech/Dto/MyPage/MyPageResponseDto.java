package com.backend.AcaTech.Dto.MyPage;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Class.CourseInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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

    public MyPageResponseDto(User user, Set<CourseInfo> userClasses) {
        this.user_name = user.getName();
        this.user_email = user.getEmail();
        this.user_phone = user.getPhone();
        this.user_major = user.getMajor();
        this.user_grade = user.getGrade();
        if (userClasses != null && !userClasses.isEmpty()) {
            StringBuilder classStringBuilder = new StringBuilder();
            for (CourseInfo classNameEntity : userClasses) {
                if (classStringBuilder.length() > 0) {
                    classStringBuilder.append(", ");
                }
                classStringBuilder.append(classNameEntity.getClassName());
            }
            this.user_class = classStringBuilder.toString();
        } else {
            this.user_class = "";
        }
        this.user_image = user.getImage();
    }
}