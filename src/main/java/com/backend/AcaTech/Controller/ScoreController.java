package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping("/student/{id}/grade")
    public Long createStudentGrade(@PathVariable Long id, @RequestBody ScoreCreateRequestDto requestDto) {
        return scoreService.create(id, requestDto);
    }
}
