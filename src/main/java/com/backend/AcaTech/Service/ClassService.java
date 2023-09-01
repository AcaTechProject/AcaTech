package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.UserClass;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Dto.Class.ClassDetailResponseDto;
import com.backend.AcaTech.Dto.Class.ClassListResponseDto;
import com.backend.AcaTech.Dto.Class.ClassStudentListResponseDto;
import com.backend.AcaTech.Dto.Class.NewClassInfoResponseDto;
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



    // 출결 조회

    // user/{class_id}로 해당하는 수업을 듣는 학생들의 리스트를 뽑아올거임
    /*
    *  student 테이블에는 st_id로 학생1 학생2로 정보가 나와잇음
    *  user_class 테이블에는 user_id(선생) class_id(수업) id가 주어짐
    *  student_class에는 class_id랑 st_id가 있음
    * 그래서 지금 user/1을 하면 국어 김민지가 뜨고 user/2를 하면 영어A민지킴이 뜸
    *
    * 내 목표는 user/1을 하면 class_id가 1인 수업을 찾아 그 수업을 듣는 st_id에 대한 정보를 출력
    * 지금 내가 class_id로 생각하고 치는게 user_class에서 id로 들어가는것같음
    *
    * */

    // 출결 등록

//
//    public List<Student> findStudentsByClassId(Long classId) {
//        List<StudentClass> studentClasses = studentClassRepository.findStudentsById(classId);
//        List<Student> students = new ArrayList<>();
//        for (StudentClass studentClass : studentClasses) {
//            students.add(studentClass.getStudent());
//        }
//        return students;
//    }

    @Transactional
    public List<StudentListResponseDto> findStudentsByClassId(Long classId) {
        StudentClass studentClass = studentClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        List<Student> studentsInClass = studentRepository.findByClasses(studentClass);

        return studentsInClass.stream()
                .map(StudentListResponseDto::new)
                .collect(Collectors.toList());
    }

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

    @Transactional
    public NewClassInfoResponseDto getNewClassInfo(Long classId) {
        StudentClass studentClass = studentClassRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found with id: " + classId));

        List<Student> studentsInClass = studentRepository.findByClasses(studentClass);

        List<StudentListResponseDto> studentDtos = studentsInClass.stream()
                .map(StudentListResponseDto::new)
                .collect(Collectors.toList());

        NewClassInfoResponseDto newClassInfo = new NewClassInfoResponseDto();
        newClassInfo.setClassName(studentClass.getClassName());
        newClassInfo.setStudents(studentDtos);

        return newClassInfo;
    }


    @Transactional
    public Map<String, Object> findByName(Long classId) {
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

        List<StudentListResponseDto> studentDtos = studentsInClass.stream()
                .map(StudentListResponseDto::new)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("students", studentDtos);
        response.put("className", className); // 여기에 수업 이름 추가

        return response;
    }






}
