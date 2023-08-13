package com.backend.AcaTech.Repository.Score;

import com.backend.AcaTech.Domain.Score.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByOrderByIdDesc();
}
