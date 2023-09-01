package com.backend.AcaTech.Service;

import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Dto.Score.*;
import com.backend.AcaTech.Repository.Score.ScoreRepository;
import com.backend.AcaTech.Repository.Score.StudentScroeRepository;
import com.backend.AcaTech.Repository.Student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class ScoreService {


    private final StudentScroeRepository studentScroeRepository;

    private final ScoreRepository scoreRepository;

    private  final StudentRepository studentRepository;



    @Autowired

    public ScoreService(StudentRepository studentRepository, ScoreRepository scoreRepository, StudentScroeRepository studentScroeRepository) {
        this.studentRepository = studentRepository;

        this.scoreRepository = scoreRepository;
        this.studentScroeRepository = studentScroeRepository;
    }
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

    // 점수 상세 조회
    @Transactional
    public ScoreResponseDto getScoreDetails(Long studentId, Long scoreId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        StudentScore studentScore = studentScroeRepository.findByStudentAndId(student, scoreId)
                .orElseThrow(() -> new EntityNotFoundException("Score not found for student " + studentId + " with id: " + scoreId));

        List<Score> scores = scoreRepository.findByStudentScore(studentScore);

        List<ScoreResponseDto.ScoreInfo> scoreInfos = scores.stream()
                .map(score -> new ScoreResponseDto.ScoreInfo(score.getClass_name(), score.getClass_score()))
                .collect(Collectors.toList());

        return new ScoreResponseDto(studentScore, scoreInfos);
    }


    // 성적 그래프1
    @Transactional
    public List<ScoreGraphListResponseDto1> getStudentGradeGraph1(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<StudentScore> scores = studentScroeRepository.findByStudent(student);

        return scores.stream()
                .map(ScoreGraphListResponseDto1::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<ScoreGraphListResponseDto2> getStudentGradeGraph2(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        List<StudentScore> scores = studentScroeRepository.findByStudent(student);

        // 학기별로 그룹화
        Map<String, List<StudentScore>> scoresBySeason = scores.stream()
                .collect(groupingBy(StudentScore::getSco_season));

        List<ScoreGraphListResponseDto2> result = new ArrayList<>();

        for (Map.Entry<String, List<StudentScore>> entry : scoresBySeason.entrySet()) {
            String season = entry.getKey();
            List<StudentScore> seasonScores = entry.getValue();

            Map<String, List<ScoreListResponseDto.ScoreInfo>> scoreInfosByClass = new HashMap<>();

            for (StudentScore score : seasonScores) {
                for (Score scoreInfo : score.getScoreInfos()) {
                    String className = scoreInfo.getClass_name();
                    scoreInfosByClass.computeIfAbsent(className, k -> new ArrayList<>()).add(scoreInfo);
                }
            }

            List<ScoreGraphListResponseDto2.ScoreInfo> scoreInfos = new ArrayList<>();

            for (Map.Entry<String, List<ScoreListResponseDto.ScoreInfo>> classEntry : scoreInfosByClass.entrySet()) {
                String className = classEntry.getKey();
                List<ScoreListResponseDto.ScoreInfo> classScores = classEntry.getValue();

                // 과목별 평균 계산
                double averageScore = classScores.stream()
                        .mapToInt(ScoreListResponseDto.ScoreInfo::getClass_score)
                        .average()
                        .orElse(0.0);

                scoreInfos.add(new ScoreGraphListResponseDto2.ScoreInfo(className, (int) averageScore));
            }

            result.add(new ScoreGraphListResponseDto2(season, scoreInfos));
        }

        return result;
    }




}
