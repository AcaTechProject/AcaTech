package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.Sign.SignUpRequestDto;
import com.backend.AcaTech.Dto.Login.LoginRequestDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(LoginRequestDto loginRequestDto) {
        Optional<User> byUserEmail = userRepository.findByEmail(loginRequestDto.getUser_email());

        if (byUserEmail.isPresent()) {
            User userEntity = byUserEmail.get();

            if (passwordEncoder.matches(loginRequestDto.getUser_pwd(), userEntity.getPwd())) {
                // 비밀번호 일치

                if (userEntity.getAuthStatus() == 1) {
                    // 인증된 계정
                    return userEntity;
                } else {
                    // 인증되지 않은 계정
                    return null;
                }
            }
        }
        return null; // 로그인 실패
    }



    public User signUp(SignUpRequestDto signUpRequestDto) {
        if (userRepository.findByEmail(signUpRequestDto.getUser_email()).isPresent()) {
            // 이미 등록된 이메일이 있을 경우 처리
            return null;
        }

        User newUser = new User();
        newUser.setName(signUpRequestDto.getUser_name());
        newUser.setEmail(signUpRequestDto.getUser_email());
        newUser.setPwd(passwordEncoder.encode(signUpRequestDto.getUser_pwd())); // 비밀번호 해싱
        newUser.setPhone(signUpRequestDto.getUser_phone());
        newUser.setMajor(signUpRequestDto.getUser_major());
        newUser.setCode(signUpRequestDto.getUser_code());

        return userRepository.save(newUser);
    }

    public void updateAuthKey(Map<String, String> map) {
        String email = (String) map.get("email");
        String authKey = (String) map.get("authKey");

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setAuthKey(authKey);
            userRepository.save(user);
        } else {
            // 해당 이메일에 해당하는 사용자가 없을 경우 처리
            System.out.println("해당 이메일에 해당하는 사용자가 없습니다.");
        }
    }
}