package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Sign.SignUpRequestDto;
import com.backend.AcaTech.Service.EmailService;
import com.backend.AcaTech.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class MailController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService mss;

    @GetMapping("/signUpConfirm")
    public ModelAndView signUpConfirm(@RequestParam Map<String, String> map, ModelAndView mav){
        //email, authKey 가 일치할경우 authStatus 업데이트
        EmailService.updateAuthStatus(map);

        mav.addObject("display", "/view/user/signUp_confirm.jsp");
        mav.setViewName("/view/index");
        return mav;
    }

    @RequestMapping("/user/signUpConfirm")
    public void signUp(@ModelAttribute SignUpRequestDto signUpRequestDto){
        // DB에 기본정보 insert
        userService.signUp(signUpRequestDto);

        //임의의 authKey 생성 & 이메일 발송
        String authKey = mss.sendAuthMail(signUpRequestDto.getUser_email());
        signUpRequestDto.setAuthKey(authKey);

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", signUpRequestDto.getUser_email());
        map.put("authKey", signUpRequestDto.getAuthKey());
        System.out.println(map);

        //DB에 authKey 업데이트
        userService.updateAuthKey(map);
    }
}