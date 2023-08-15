package com.backend.AcaTech.Repository.Consulting;

import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultingRepository extends JpaRepository<Consulting, Integer> {
    List<Consulting> findAllByOrderByIdDesc();
}
