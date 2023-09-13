package com.backend.AcaTech.Dto.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private Long user_id;
    private String user_email;
    private String user_pwd;

    private String loggedIn_user_email;
}