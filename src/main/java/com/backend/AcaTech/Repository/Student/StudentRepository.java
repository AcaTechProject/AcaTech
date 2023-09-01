package com.backend.AcaTech.Repository.Student;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByOrderByIdDesc();


    //0902 수정
    List<Student> findByClasses(StudentClass studentClass);
//    List<Student> findByClasses(List<StudentClass> classes);

    List<Student> findByClassesContains(StudentClass studentClass);
    //Optional<Student> findByIdAndStudentId(Long conId, Long studentId);


    // 새로운 메서드
    @Query("SELECT s FROM Student s JOIN s.classes c WHERE c IN :classes")
    List<Student> findByMultipleClasses(@Param("classes") List<StudentClass> classes);





}
