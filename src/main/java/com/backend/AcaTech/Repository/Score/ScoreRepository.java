package com.backend.AcaTech.Repository.Score;

import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByOrderByIdDesc();

    List<Score> findByStudentScore(StudentScore studentScore);

    //List<StudentScore> findByStudent(Student student);
}
