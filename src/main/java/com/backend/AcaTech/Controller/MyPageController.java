package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.MyPage.MyPageResponseDto;
import com.backend.AcaTech.Dto.MyPage.MyPageUpdateRequestDto;
import com.backend.AcaTech.Service.MyPageService;
import com.backend.AcaTech.Repository.Class.ClassNameRepository;
import com.backend.AcaTech.Repository.Class.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MyPageController {

    private final MyPageService myPageService;
    private final ClassNameRepository classNameRepository;
    private final UserRepository userRepository;

    @GetMapping("/user/{id}")
    public ResponseEntity<MyPageResponseDto> searchById(@PathVariable Long id) {
        Optional<User> userOptional = myPageService.searchById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Set<CourseInfo> userClasses = user.getClasses();
            MyPageResponseDto responseDto = new MyPageResponseDto(user, userClasses);
            return ResponseEntity.ok(responseDto); //200
        } else {
            return ResponseEntity.notFound().build(); //404
        }
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

            // 클래스 이름을 쉼표와 공백으로 구분하여 배열로 분리
            String[] classNames = classListString.split(", ");

            Set<CourseInfo> userClassesSet = new HashSet<>();

            for (String className : classNames) {
                // 클래스 이름으로 해당 클래스를 찾음
                CourseInfo existingClass = classNameRepository.findByClassName(className);

                if (existingClass != null) {
                    userClassesSet.add(existingClass);
                } else {
                    // 클래스가 존재하지 않는 경우, 새로운 클래스 생성 및 저장
                    CourseInfo newClass = new CourseInfo(className);
                    classNameRepository.save(newClass);
                    userClassesSet.add(newClass);
                }
            }

            // 사용자의 클래스 정보를 업데이트
            user.setClasses(userClassesSet);
        }


        if (updateRequestDto.getUser_image() != null) {
            user.setImage(updateRequestDto.getUser_image());
        }

        myPageService.updateUserInformation(user.getId(), updateRequestDto);

        return user;
    }
}