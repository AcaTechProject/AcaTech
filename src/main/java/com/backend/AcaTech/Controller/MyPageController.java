package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.MyPage.MyPageResponseDto;
import com.backend.AcaTech.Dto.MyPage.MyPageUpdateRequestDto;
import com.backend.AcaTech.Service.MyPageService;
import com.backend.AcaTech.Repository.Class.ClassNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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
            String classListString = updateRequestDto.getUser_class();

            String[] classNames = classListString.split(", ");

            List<CourseInfo> userClasses = new ArrayList<>();

            for (String className : classNames) {
                CourseInfo existingClass = classNameRepository.findByClassName(className);

                if (existingClass != null) {
                    userClasses.add(existingClass);
                } else {
                    CourseInfo newClass = new CourseInfo(className);
                    classNameRepository.save(newClass);
                    userClasses.add(newClass);
                }
            }

            Set<CourseInfo> userClassesSet = new HashSet<>(userClasses);
            user.setClasses(userClassesSet);
        }

        if (updateRequestDto.getUser_image() != null) {
            user.setImage(updateRequestDto.getUser_image());
        }

        myPageService.updateUserInformation(user.getId(), updateRequestDto);

        return user;
    }
}