package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/signUpConfirm")
    public ModelAndView signUpConfirm(@RequestParam String email, @RequestParam String authKey) {
        ModelAndView mav = new ModelAndView();
        boolean isVerified = emailService.verifyEmail(email, authKey);
        if (isVerified) {
            mav.addObject("message", "이메일 인증이 완료되었습니다.");
        } else {
            mav.addObject("message", "이메일 인증에 실패했습니다. 인증 키를 다시 확인하세요.");
        }
        mav.setViewName("confirmation");
        return mav;
    }
}