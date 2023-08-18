package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Utils.MailUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    public String sendAuthMail(String toEmail) {
        // 임의의 인증키 생성 (여기서는 간단하게 현재 시간을 이용한 랜덤 값으로 생성)
        String authKey = String.valueOf(System.currentTimeMillis());
        // 이메일 전송
        sendEmailVerification(toEmail, authKey);
        return authKey;
    }

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailVerification(String toEmail, String authKey) {
        String subject = "이메일 인증 안내";
        String content = generateVerificationLink(toEmail, authKey);
        String fromName = "AcaTech";
        String fromEmail = "noreply@AcaTech.com";

        try {
            MailUtils mailUtils = new MailUtils(mailSender);
            mailUtils.sendMail(toEmail, subject, content, fromName, fromEmail);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateVerificationLink(String toEmail, String authKey) {
        String baseUrl = "http://localhost:8080";
        return baseUrl + "/verify?email=" + toEmail + "&authKey=" + authKey;
    }

    @Autowired
    private static UserRepository userRepository;

    public static void updateAuthStatus(Map<String, String> map) {
        String email = map.get("email");
        String authKey = map.get("authKey");

        Optional<User> userOptional = userRepository.findByEmailAndAuthKey(email, authKey);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setAuthStatus(1); // 인증 완료로 업데이트
            userRepository.save(user);
        } else {
        }
    }
}