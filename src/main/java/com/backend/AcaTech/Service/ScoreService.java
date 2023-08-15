package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Score.ScoreListResponseDto;
import com.backend.AcaTech.Dto.Score.ScoreResponseDto;
import com.backend.AcaTech.Repository.Score.ScoreRepository;
import com.backend.AcaTech.Repository.Score.StudentScroeRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScoreService {

    @Autowired
    private final StudentScroeRepository studentScroeRepository;
    @Autowired
    private final ScoreRepository scoreRepository;
    @Autowired
    private  final StudentRepository studentRepository;




    @Transactional
    public Long create(Long id, ScoreCreateRequestDto requestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        StudentScore studentScore = StudentScore.builder()
                .student(student)
                .sco_season(requestDto.getSco_season())
                .sco_test(requestDto.getSco_test())
                .writer(requestDto.getWriter())
                .score_date(requestDto.getScore_date())
                .build();

        StudentScore savedStudentScore = studentScroeRepository.save(studentScore);

        if(requestDto.getScoreInfos() != null) {
            for (ScoreCreateRequestDto.ScoreInfo scoreInfo : requestDto.getScoreInfos()) {
                Score score = Score.builder()
                        .studentScore(savedStudentScore)
                        .class_name(scoreInfo.getClass_name())
                        .class_score(scoreInfo.getClass_score())
                        .build();
                scoreRepository.save(score);
            }
        }
        return savedStudentScore.getId();
    }


    // 점수 전체 조회
    @Transactional
    public List<ScoreListResponseDto> getStudentGrades(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<StudentScore> scores = studentScroeRepository.findByStudent(student);

        return scores.stream()
                .map(ScoreListResponseDto::new)
                .collect(Collectors.toList());
    }
}
