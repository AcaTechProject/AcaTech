package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Dto.MyPage.MyPageUpdateRequestDto;
import com.backend.AcaTech.Repository.Class.ClassNameRepository;
import com.backend.AcaTech.Repository.MyPage.MyPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyPageService {

    private final MyPageRepository myPageRepository;
    private final ClassNameRepository classNameRepository;

    @Autowired
    public MyPageService(MyPageRepository myPageRepository, ClassNameRepository classNameRepository) {
        this.myPageRepository = myPageRepository;
        this.classNameRepository = classNameRepository;
    }

    public Optional<User> searchById(Long id) {
        return myPageRepository.findById(id);
    }

    public Optional<CourseInfo> getClassNameById(Long id) {
        return classNameRepository.findById(id);
    }

    public User updateUserInformation(Long userId, MyPageUpdateRequestDto updateRequestDto) {
        Optional<User> userOptional = myPageRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (updateRequestDto.getUser_image() != null) {
                user.setImage(updateRequestDto.getUser_image());
            }
            if (updateRequestDto.getUser_email() != null) {
                user.setEmail(updateRequestDto.getUser_email());
            }
            if (updateRequestDto.getUser_phone() != null) {
                user.setPhone(updateRequestDto.getUser_phone());
            }

            if (updateRequestDto.getUser_class() != null) {
                CourseInfo className = classNameRepository.findByClassName(updateRequestDto.getUser_class());
                if (className != null) {
                    user.getClasses().clear();
                    user.getClasses().add(className);
                }
            }
            
            if (updateRequestDto.getUser_grade() != null) {
                user.setGrade(updateRequestDto.getUser_grade());
            }
            return myPageRepository.save(user);
        } else {
            return null;
        }
    }
}