package com.backend.AcaTech.Controller;


import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.MyPage.MyPageResponseDto;
import com.backend.AcaTech.Dto.MyPage.MyPageUpdateRequestDto;
import com.backend.AcaTech.Service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ResponseBody
@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping(value = "/user/{id}")
    public MyPageResponseDto searchById(@PathVariable Long id) {
        Optional<User> userOptional = myPageService.searchById(id);
        Optional<CourseInfo> classNameOptional = myPageService.getClassNameById(id);
        User user = userOptional.orElse(null);
        CourseInfo className = classNameOptional.orElse(null);
        if (user != null && className != null) {
            return new MyPageResponseDto(user, className);
        } else {
            return null;
        }
    }

    @PatchMapping("/user/{id}")
    public User updateUserInformation(@PathVariable Long id, @RequestBody MyPageUpdateRequestDto updateRequestDto) {
        return myPageService.updateUserInformation(id, updateRequestDto);
    }
}