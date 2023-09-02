package com.backend.AcaTech.Dto.MyPage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageUpdateRequestDto {
    private String user_image;
    private String user_phone;
    private String user_email;
    private String user_class;
    private String user_grade;
}