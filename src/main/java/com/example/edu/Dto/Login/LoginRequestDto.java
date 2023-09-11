package com.example.edu.Dto.Login;

import com.example.edu.Domain.User.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    private String user_email;
    private String user_pwd;

    private String loggedIn_user_email;
}