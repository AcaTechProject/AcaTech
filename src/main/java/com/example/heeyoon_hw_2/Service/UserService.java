package com.example.heeyoon_hw_2.Service;

import com.example.heeyoon_hw_2.Domain.StudentClass;
import com.example.heeyoon_hw_2.Dto.CourseWithStudentsDto;
import com.example.heeyoon_hw_2.Dto.StudentDto;
import com.example.heeyoon_hw_2.Repository.StudentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class UserService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    public CourseWithStudentsDto getStudentByClassId(Long classId) {
        List<StudentClass> studentClasses = studentClassRepository.findByCourse(classId);

        if(studentClasses.isEmpty() || studentClasses.get(0).getCourse() == null) {
            return null;
        }

        CourseWithStudentsDto courseDto = new CourseWithStudentsDto();
        courseDto.setSubject(studentClasses.get(0).getCourse().getCourseName().split(" ")[0]); // "국어A 김민지"에서 "국어A"만 추출

        List<StudentDto> studentDtos = new ArrayList<>();
        for(StudentClass sc : studentClasses) {
            StudentDto studentDto = new StudentDto();
            studentDto.setSt_name(sc.getStudent().getSt_name());
            studentDto.setClass_name(sc.getCourse().getCourseName());
            studentDtos.add(studentDto);
        }
        courseDto.setUserClass(studentDtos);

        return courseDto;
    }
}
