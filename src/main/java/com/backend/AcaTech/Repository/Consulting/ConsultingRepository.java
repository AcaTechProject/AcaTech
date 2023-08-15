package com.backend.AcaTech.Repository.Consulting;

import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Score.Score;
import com.backend.AcaTech.Domain.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultingRepository extends JpaRepository<Consulting, Integer> {
    List<Consulting> findAllByOrderByIdDesc();

    List<Consulting> findByStudent(Student student);

    Optional<Consulting> findByIdAndStudentId(Long conId, Long studentId);
}
