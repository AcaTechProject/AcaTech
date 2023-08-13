package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentResponseDto;
import com.backend.AcaTech.Service.StudentService;
import lombok.RequiredArgsConstructor;
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
}
