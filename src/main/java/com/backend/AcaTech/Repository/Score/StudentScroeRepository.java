package com.backend.AcaTech.Repository.Score;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Score.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentScroeRepository extends JpaRepository<StudentScore, Long> {
    List<StudentScore> findAllByOrderByIdDesc();
}
