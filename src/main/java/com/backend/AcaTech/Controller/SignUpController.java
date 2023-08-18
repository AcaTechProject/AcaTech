package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.Sign.SignUpRequestDto;
import com.backend.AcaTech.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signupForm";
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signUp(signUpRequestDto);
        if (user != null) {
            //return ResponseEntity.ok("User registered successfully");
            String responseMessage = signUpRequestDto.getUser_name() + "\n" + signUpRequestDto.getUser_email() + "\n" + signUpRequestDto.getUser_pwd()
                    + "\n" + signUpRequestDto.getUser_phone()+ "\n" + signUpRequestDto.getUser_major()+ "\n" + signUpRequestDto.getUser_code();
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed");
        }
    }
}