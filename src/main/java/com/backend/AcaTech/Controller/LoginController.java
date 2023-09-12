package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.Login.LoginRequestDto;
import com.backend.AcaTech.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    //로그인
    @GetMapping("/login")
    public String loginForm(Model model) {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/user/login/{id}")
    public ResponseEntity<String> login(@RequestBody String requestBody, HttpSession session) {
        ObjectMapper objectMapper = new ObjectMapper(); // JSON 파싱을 위한 ObjectMapper

        try {
            LoginRequestDto loginRequestDto = objectMapper.readValue(requestBody, LoginRequestDto.class);
            User loggedInUser = userService.login(loginRequestDto);

            if (loggedInUser != null) {
                // 로그인 성공
                session.setAttribute("userId", loggedInUser.getId()); // 사용자 ID를 세션에 저장
                String responseMessage = "로그인 성공: " + loggedInUser.getEmail();
                return ResponseEntity.ok(responseMessage);
            } else {
                // 로그인 실패
                String responseMessage = "로그인 실패";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMessage);
            }
        } catch (JsonProcessingException e) {
            // JSON 파싱 에러 처리
            String errorMessage = "JSON 파싱 에러: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("userId"); // 세션에서 사용자 ID 제거
        String responseMessage = "로그아웃 성공";
        return ResponseEntity.ok(responseMessage);
    }
}
