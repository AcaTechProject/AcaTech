package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.UserClass;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Dto.Class.ClassDetailResponseDto;
import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Dto.Class.ClassStudentListResponseDto;
import com.backend.AcaTech.Dto.Class.NewClassInfoResponseDto;
import com.backend.AcaTech.Dto.Student.StudentListForClassIdDto;
import com.backend.AcaTech.Dto.Student.StudentListResponseDto;
import com.backend.AcaTech.Repository.Class.ClassNameRepository;
import com.backend.AcaTech.Repository.Class.UserClassRepository;
import com.backend.AcaTech.Repository.Student.StudentClassRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClassService {

    private final UserClassRepository userClassRepository;
//    private final ClassNameRepository classNameRepository;
    public UserClass entity;


    private final StudentClassRepository studentClassRepository;
    private Student studentEntity;
    private StudentClass studentClassEntity;

    @Autowired
    private StudentRepository studentRepository;


//    public CourseInfo courseEntity;

//
//    @Transactional
//    public List<BoardListResponseDto> searchAllDesc() {
//        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
//                .map(BoardListResponseDto::new)
//                .collect(Collectors.toList());
//    }

//    @Transactional
////    public ClassListResponseDto searchById(Long id) {
////        UserClass userClass = userClassRepository.findById(id).orElseThrow(()
////                -> new IllegalArgumentException("해당 과목이 없습니다."));
////        return new ClassListResponseDto(userClass);
////    }

    @Transactional
    public List<ClassListResponseDto> searchById(Long id) {
        return userClassRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(ClassListResponseDto::new)
                .collect(Collectors.toList());
    }






    // classId로 출결 과목 조회
    @Transactional
    public ClassDetailResponseDto getClassDetailsByClassId(Long classId) {
        // UserClassRepository를 사용해 해당 classId로 UserClass를 찾습니다.
        UserClass userClass = userClassRepository.findById(classId).orElseThrow(()
                -> new IllegalArgumentException("해당 수업 없음"));

        // UserClass 엔터티를 DTO에 전달하여 반환합니다.
        return new ClassDetailResponseDto(userClass);
    }

    private String parseCourseName(String className) {
        int spaceIndex = className.indexOf(" ");
        if (spaceIndex != -1) {
            return className.substring(0, spaceIndex);
        }
        return className;  // 공백이 없는 경우 전체 문자열 반환
    }



//
//    @Transactional
//    public List<StudentListResponseDto> findStudentsByClassId(Long classId) {
//        StudentClass studentClass = studentClassRepository.findById(classId)
//                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));
//
//        List<Student> studentsInClass = studentRepository.findByClasses(studentClass);
//
//        return studentsInClass.stream()
//                .map(StudentListResponseDto::new)
//                .collect(Collectors.toList());
//    }



    @Transactional
    public ClassStudentListResponseDto findClassAndStudents(Long classId) {
        // 해당 ID의 Class 객체를 찾음
        StudentClass studentClass = studentClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        // Class에 속한 학생 목록을 찾음
        List<Student> studentsInClass = studentRepository.findByClasses(studentClass);

        return new ClassStudentListResponseDto(studentClass, studentsInClass);
    }
//
//    @Transactional
//    public NewClassInfoResponseDto getNewClassInfo(Long classId) {
//        // 기존 로직
//        StudentClass studentClass = studentClassRepository.findById(classId)
//                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));
//
//        List<Student> studentsInClass = studentRepository.findByClasses(studentClass);
//        List<StudentListResponseDto> studentDtos = studentsInClass.stream()
//                .map(StudentListResponseDto::new)
//                .collect(Collectors.toList());
//
//        NewClassInfoResponseDto newClassInfo = new NewClassInfoResponseDto();
//        newClassInfo.setClassName(studentClass.getClassName());
//        newClassInfo.setStudents(studentDtos);
//
//        return newClassInfo;
//    }
//


//
//    @Transactional
//    public NewClassInfoResponseDto getNewClassInfo(Long classId) {
//        StudentClass studentClass = studentClassRepository.findById(classId)
//                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));
//
//        List<Student> studentsInClass = studentRepository.findByClasses(studentClass);
//
//        List<StudentListResponseDto> studentDtos = studentsInClass.stream()
//                .map(StudentListResponseDto::new)
//                .collect(Collectors.toList());
//
//        NewClassInfoResponseDto newClassInfo = new NewClassInfoResponseDto();
//        newClassInfo.setClassName(studentClass.getClassName());
//        newClassInfo.setStudents(studentDtos);
//
//        return newClassInfo;
//    }


    private void validateStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("Invalid studentId");
        }
    }





    @Transactional
    public Map<String, Object> findByName(Long classId) {

        if (classId == null || classId <= 0) {
            throw new IllegalArgumentException("존재하지 않는 수업");
        }

        StudentClass studentClass = studentClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        String className = studentClass.getClassName();

        List<StudentClass> studentClasses = studentClassRepository.findByClassName(className);

        if (studentClasses.isEmpty()) {
            throw new EntityNotFoundException("Class not found with name: " + className);
        }

        List<Student> studentsInClass = studentClasses.stream()
                .flatMap(sClass -> studentRepository.findByClasses(sClass).stream())
                .collect(Collectors.toList());

        List<StudentListForClassIdDto> studentDtos = studentsInClass.stream()
                .map(StudentListForClassIdDto::new)
                .collect(Collectors.toList());

        // 여기에서 각 학생의 className을 설정
        for(StudentListForClassIdDto studentDto : studentDtos) {
            studentDto.setClassName(className);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("students", studentDtos);
        response.put("className", className);

        return response;
    }






}
