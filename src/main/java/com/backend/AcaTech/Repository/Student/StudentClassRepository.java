package com.backend.AcaTech.Repository.Student;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    List<StudentClass> findAllByOrderByIdDesc();

    List<StudentClass> findByStudent(Student student);

    void deleteByStudent(Student student);

    List<StudentClass> findByClassName(String className);
}
