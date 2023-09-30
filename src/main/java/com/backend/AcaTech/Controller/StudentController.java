package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Response.ResponseMessage;
import com.backend.AcaTech.Dto.Student.*;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceTotalResponseDto;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import com.backend.AcaTech.Service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @PostMapping("/student")
    public ResponseMessage createStudent(@RequestBody StudentCreateRequestDto requestDto) {
        try {
            // 학생 생성 로직
            Long studentId = studentService.createStudent(requestDto);

            // 저장된 데이터를 조회
            Student savedStudent = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

            // 저장된 데이터를 응답 메시지에 추가
            return new ResponseMessage<>(true, "학생 등록 성공", savedStudent);
        } catch (IllegalArgumentException ex) {
            return new ResponseMessage<>(false, ex.getMessage(), null);
        } catch (Exception e) {
            return new ResponseMessage<>(false, "서버 오류: " + e.getMessage(), null);
        }
    }


    @GetMapping("/student/{id}")
    public StudentResponseDto searchById(@PathVariable Long id) {
        return studentService.searchById(id);
    }

    // 학생 정보 삭제
    @DeleteMapping("/student/{id}")
    public ResponseEntity<ResponseMessage<Void>> deleteStudent(@PathVariable Long id) {
        ResponseMessage<Void> responseMessage = studentService.delete(id);
        HttpStatus status = responseMessage.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(responseMessage);
    }

    // 학생 정보 수정
    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequestDto requestDto) {
        studentService.updateStudent(id, requestDto);
        return ResponseEntity.ok("학생 정보가 업데이트되었습니다.");
    }

    // 학생 출석 리스트
    @GetMapping("/student/{id}/student_attendance")
    public List<StudentAttendanceListResponseDto> getStudentAttendance(@PathVariable Long id) {
        return studentService.searchAllAttendanceList(id);
    }

    // 학생 출석 통계
    @GetMapping("/student/{studentId}/attendance_total")
    public ResponseEntity<StudentAttendanceTotalResponseDto> getAttendanceStatistics(@PathVariable Long studentId) {
        StudentAttendanceTotalResponseDto statistics = studentService.getAttendanceStatistics(studentId);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }


    // 학생 개별 메시지
    @GetMapping("/student/{id}/message_info")
    public StudentMessageResponseDto studentMessage(@PathVariable Long id) {
        return studentService.studentMessage(id);
    }


    // 학생 리스트 전체 조회
    @GetMapping("/student/all")
    public List<StudentListResponseDto> searchAllDesc() {
        return studentService.studentList();
    }


    // 특정 수업을 듣는 학생 리스트 조회
//    @GetMapping("/student/byClass/{classId}")
//    public List<StudentListResponseDto> searchByClassId(@PathVariable Long classId) {
//        return studentService.findByClassId(classId);
//    }


    // 특정 수업을 듣는 학생 리스트 조회 (classId로)
    @GetMapping("/student/byClass/{classId}")
    public ResponseEntity<ResponseMessage<List<StudentListResponseDto>>> searchByClassName(@PathVariable Long classId) {
        List<StudentListResponseDto> studentList = studentService.findByName(classId);

        if (studentList.isEmpty()) {
            // 수업을 듣는 학생이 없는 경우
            ResponseMessage<List<StudentListResponseDto>> responseMessage = new ResponseMessage<>(false, "No students found for classId: " + classId, null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseMessage);
        } else {
            ResponseMessage<List<StudentListResponseDto>> responseMessage = new ResponseMessage<>(true, "Students found for classId: " + classId, studentList);
            return ResponseEntity.ok(responseMessage);
        }
    }

    // 특정 수업을 듣는 학생 리스트 조회 -> 메시지 부분
    @GetMapping("/student/byClass/message/{classId}")
    public List<StudentForMessageListResponseDto> searchByClassNameForMessage(@PathVariable Long classId) {
        return studentService.findByNameForMessage(classId);
    }
}
