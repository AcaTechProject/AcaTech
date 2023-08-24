package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Dto.Student.*;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceTotalResponseDto;
import com.backend.AcaTech.Service.StudentService;
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

    @PostMapping("/student")
    public Long createStudent(@RequestBody StudentCreateRequestDto requestDto) {
        return studentService.createStudent(requestDto);
    }

    @GetMapping("/student/{id}")
    public StudentResponseDto searchById(@PathVariable Long id) {
        return studentService.searchById(id);
    }

    // 학생 정보 삭제
    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {studentService.delete(id);}

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
}
