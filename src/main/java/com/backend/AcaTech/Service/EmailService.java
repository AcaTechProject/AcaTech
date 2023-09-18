package com.backend.AcaTech.Service;

import com.backend.AcaTech.Utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean verifyEmail(String email, String authKey) {
        // 이메일과 인증 키를 확인하고 DB에서 사용자 인증 상태 업데이트
        // 성공하면 true, 실패하면 false를 반환
        return true;
    }

    public void sendAuthMail(String to, String authKey) throws MessagingException, UnsupportedEncodingException {
        String subject = "이메일 인증";
        String content = "클릭하여 이메일을 인증하세요: " + getConfirmationLink(to, authKey);
        String fromName = "Your Name";
        String fromEmail = "your-email@example.com";

        try {
            MailUtils mailUtils = new MailUtils(mailSender);
            mailUtils.sendMail(to, subject, content, fromName, fromEmail);
        } catch (Exception e) {
            // 예외 처리 코드 추가
            e.printStackTrace();
        }
    }

    private String getConfirmationLink(String email, String authKey) {
        return "http://yourdomain.com/signUpConfirm?email=" + email + "&authKey=" + authKey;
    }
}