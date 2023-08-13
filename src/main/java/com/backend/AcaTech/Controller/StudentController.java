package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentResponseDto;
import com.backend.AcaTech.Dto.Student.StudentUpdateRequestDto;
import com.backend.AcaTech.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StudentController {

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

    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateRequestDto requestDto) {
        studentService.updateStudent(id, requestDto);
        return ResponseEntity.ok("학생 정보가 업데이트되었습니다.");
    }
}
