package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.MyPage.MyPageResponseDto;
import com.backend.AcaTech.Dto.MyPage.MyPageUpdateRequestDto;
import com.backend.AcaTech.Service.MyPageService;
import com.backend.AcaTech.Repository.Class.ClassNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ResponseBody
@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    private final ClassNameRepository classNameRepository;

    @GetMapping("/user/{id}")
    public MyPageResponseDto searchById(@PathVariable Long id) {
        Optional<User> userOptional = myPageService.searchById(id);
        Optional<CourseInfo> classNameOptional = myPageService.getClassNameById(id);
        User user = userOptional.orElseGet(() -> new User());
        CourseInfo className = classNameOptional.orElseGet(() -> new CourseInfo());
        return new MyPageResponseDto(user, className);
    }

    @PatchMapping("/user/{id}")
    public User updateUserInformation(@PathVariable Long id, @RequestBody MyPageUpdateRequestDto updateRequestDto) {
        Optional<User> userOptional = myPageService.searchById(id);
        User user = userOptional.orElseGet(() -> new User());
        return user;
    }
}
