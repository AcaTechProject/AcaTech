package com.backend.AcaTech.Repository.Student;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentFamily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentFamilyRepository extends JpaRepository<StudentFamily, Long> {
    List<StudentFamily> findAllByOrderByIdDesc();
}
