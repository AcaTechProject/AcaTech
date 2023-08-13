package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import com.backend.AcaTech.Dto.Score.ScoreCreateRequestDto;
import com.backend.AcaTech.Dto.Student.StudentCreateRequestDto;
import com.backend.AcaTech.Repository.Score.ScoreRepository;
import com.backend.AcaTech.Repository.Score.StudentScroeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScoreService {

    private final StudentScroeRepository studentScroeRepository;
    private final ScoreRepository scoreRepository;
    @Transactional
    public Long create(ScoreCreateRequestDto requestDto) {
        StudentScore studentScore = StudentScore.builder()
                .student(requestDto.getStudent())  // Set the student
                .sco_season(requestDto.getSco_season())
                .sco_test(requestDto.getSco_test())
                .wirter(requestDto.getWriter())
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

}
