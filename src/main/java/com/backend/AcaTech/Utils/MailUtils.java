package com.backend.AcaTech.Utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class MailUtils {
    private JavaMailSender mailSender;

    public MailUtils(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String content)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setSubject(subject);
        helper.setText(content, true);
        //helper.setFrom(fromEmail, fromName);
        helper.setTo(to);

        mailSender.send(message);
    }
}