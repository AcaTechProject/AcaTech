package com.backend.AcaTech.Repository.Score;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.StudentScore;
import com.backend.AcaTech.Domain.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentScroeRepository extends JpaRepository<StudentScore, Long> {
    List<StudentScore> findAllByOrderByIdDesc();

    List<StudentScore> findByStudent(Student student);



    Optional<StudentScore> findByStudentAndId(Student student, Long id);
}
