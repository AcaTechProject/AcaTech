package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.MyPage.MyPageResponseDto;
import com.backend.AcaTech.Dto.MyPage.MyPageUpdateRequestDto;
import com.backend.AcaTech.Service.MyPageService;
import com.backend.AcaTech.Repository.Class.ClassNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

        // 업데이트 요청 데이터를 반영합니다.
        if (updateRequestDto.getUser_email() != null) {
            user.setEmail(updateRequestDto.getUser_email());
        }
        if (updateRequestDto.getUser_phone() != null) {
            user.setPhone(updateRequestDto.getUser_phone());
        }
        if (updateRequestDto.getUser_grade() != null) {
            user.setGrade(updateRequestDto.getUser_grade());
        }

        if (updateRequestDto.getUser_class() != null) {
            String className = updateRequestDto.getUser_class();

            CourseInfo existingClass = classNameRepository.findByClassName(className);

            if (existingClass != null) {
                user.getClasses().clear();
                user.getClasses().add(existingClass);
            } else {
                CourseInfo newClass = new CourseInfo(className);
                classNameRepository.save(newClass);
                user.getClasses().clear();
                user.getClasses().add(newClass);
            }
        }

        if (updateRequestDto.getUser_image() != null) {
            user.setImage(updateRequestDto.getUser_image());
        }

        myPageService.updateUserInformation(user.getId(), updateRequestDto);

        return user;
    }
}