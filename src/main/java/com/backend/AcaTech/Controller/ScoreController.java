package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScoreController {

    @Autowired
    private final ScoreService scoreService;

    // 해당 학생 성적 등록
    @PostMapping("/student/{id}/grade")
    public Long createStudentGrade(@PathVariable Long id, @RequestBody ScoreCreateRequestDto requestDto) {
        return scoreService.create(id, requestDto);
    }

    // 해당 학생 성적 전체 조회
    @GetMapping("/student/{studentId}/grade")
    public List<ScoreListResponseDto> getStudentGrades(@PathVariable Long studentId) {
        return scoreService.getStudentGrades(studentId);
    }
}
