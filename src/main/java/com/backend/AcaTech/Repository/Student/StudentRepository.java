package com.backend.AcaTech.Repository.Student;

import com.backend.AcaTech.Domain.Class.ClassName;
import com.backend.AcaTech.Domain.Student.Student;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByOrderByIdDesc();
}
