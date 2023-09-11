package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Class.UserClass;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentAttendance;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Dto.Class.*;
import com.backend.AcaTech.Dto.Student.StudentListForClassIdDto;
import com.backend.AcaTech.Repository.Class.UserClassRepository;
import com.backend.AcaTech.Repository.Student.StudentAttendanceRepository;
import com.backend.AcaTech.Repository.Student.StudentClassRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClassService {

    private final UserClassRepository userClassRepository;
    public UserClass entity;

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    private final StudentClassRepository studentClassRepository;
    private Student studentEntity;
    private StudentClass studentClassEntity;

    @Autowired
    private StudentRepository studentRepository;
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

    // 해당 유저 담당 수업 조회
    @Transactional
    public List<ClassListResponseDto> searchById(Long id) {
        return userClassRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(ClassListResponseDto::new)
                .collect(Collectors.toList());
    }


    // 희윤

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


    //유효성 검사
    private void validateStudentId(Long studentId) {
        if (studentId == null || studentId <= 0) {
            throw new IllegalArgumentException("Invalid studentId");
        }
    }


    //현재 출결내용 조회
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
        int numbering = 1;
        for (StudentListForClassIdDto studentDto : studentDtos) {
            studentDto.setClassName(className);
            studentDto.setNum(numbering++); // 넘버링 설정 및 증가
        }


        // 여기에서 각 학생의 className을 설정
        for (StudentListForClassIdDto studentDto : studentDtos) {
            studentDto.setClassName(className);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("students", studentDtos);
        response.put("className", className);

        return response;
    }


    //지난 출결내역 조회
    public List<PreviousAttendanceDto> getPreviousAttendances(Long classId) {
        List<StudentAttendance> attendances = studentAttendanceRepository.findByStudentClass_Id(classId);
        if (attendances.isEmpty()) {
            throw new EntityNotFoundException("해당 classId에 맞는 수업 조회 불가 : " + classId);
        }

        Map<String, PreviousAttendanceDto> attendanceSummaryMap = new HashMap<>();

        for (StudentAttendance attendance : attendances) {
            String date = attendance.getAtt_date().toString();
            PreviousAttendanceDto dto = attendanceSummaryMap.getOrDefault(date, new PreviousAttendanceDto());

            dto.setDateTime(attendance.getAtt_date());
            dto.setClassName(attendance.getStudentClass().getClassName());

            // 출결 정보를 문자열로
            String attendanceInfo = String.format("출석%s 지각%s 결석%s 기타%s",
                    attendance.getAtt_o(),
                    attendance.getAtt_late(),
                    attendance.getAtt_x(),
                    attendance.getAtt_etc());

            // 기존 정보에 새로운 정보를 누적
            dto.addAttendanceInfo(attendanceInfo);
            attendanceSummaryMap.put(date, dto);
        }

        // Map의 Value를 List로 변환
        List<PreviousAttendanceDto> sortedList = new ArrayList<>(attendanceSummaryMap.values());

        // List를 dateTime을 기준으로 정렬
        sortedList.sort(Comparator.comparing(PreviousAttendanceDto::getDateTime).reversed());

        return sortedList;
    }


    // 지난 출결 정보 수정
    public void updateMultipleAttendances(AttendanceUpdateRequestDto requestDto) {
        Long classId = requestDto.getClassId();
        LocalDate attDate = requestDto.getAttDate();

        for (AttendanceUpdateRequestDto.StudentAttendanceDto student : requestDto.getStudents()) {
            Long studentId = student.getStId();

            // 변경: List<StudentAttendance>를 반환하도록 함
            List<StudentAttendance> attendances = studentAttendanceRepository.findByConditions(attDate, classId, studentId);

            if (attendances.isEmpty()) {
                throw new EntityNotFoundException("Attendance not found for classId: " + classId + ", att_date: " + attDate + ", and studentId: " + studentId);
            }

            // 변경: 모든 찾은 레코드를 업데이트
            for (StudentAttendance attendance : attendances) {
                attendance.setAtt_o(student.getAtt_o());
                attendance.setAtt_late(student.getAtt_late());
                attendance.setAtt_x(student.getAtt_x());
                attendance.setAtt_etc(student.getAtt_etc());
                attendance.setAtt_reason(student.getAtt_reason());

                studentAttendanceRepository.save(attendance);
            }
        }
    }


}
