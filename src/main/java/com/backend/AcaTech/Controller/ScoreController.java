package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Consulting.ConsultingResponseDto;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreGraphListResponseDto1;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Dto.Score.ScoreResponseDto;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ScoreController {


    private final ScoreService scoreService;
    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

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

    @GetMapping("/student/{studentId}/grade/{scoreId}")
    public ResponseEntity<ScoreResponseDto> getConsultingDetail(
            @PathVariable Long studentId,
            @PathVariable Long scoreId) {
        ScoreResponseDto scoreResponseDto = scoreService.getScoreDetails(studentId, scoreId);

        if (scoreResponseDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(scoreResponseDto);
    }


    // 성적 그래프 1
    @GetMapping("/student/{studentId}/grade/graph1")
    public List<ScoreGraphListResponseDto1> getStudentGradeGraph(@PathVariable Long studentId) {
        return scoreService.getStudentGradeGraph(studentId);
    }

}
