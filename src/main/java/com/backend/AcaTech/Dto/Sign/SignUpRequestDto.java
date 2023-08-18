package com.backend.AcaTech.Dto.Sign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {

    private String user_name;
    private String user_email;
    private String user_pwd;
    private String user_phone;
    private String user_major;
    private String user_code;
    private String authKey;
    private int auth_status;
}