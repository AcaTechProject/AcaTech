package com.backend.AcaTech.Repository.Student;

import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentAttendance;
import com.backend.AcaTech.Domain.Student.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Integer> {
    List<StudentAttendance> findAllByOrderByIdDesc();

    List<StudentAttendance> findByStudent(Student student);


    // 희윤
    // 지난 출결 조회
    //    List<StudentAttendance> findByClassId(Long classId);
    List<StudentAttendance> findByStudentClass_Id(Long classId);

    //지난 출결 정보 수정
    //Optional<StudentAttendance> findByIdAndStudentClass_Id(Long id, Long classId);

    //    Optional<StudentAttendance> findByAtt_dateAndStudentClass_IdAndStudent_Id(LocalDate attDate, Long classId, Long stId);
//Optional<StudentAttendance> findByAttDateAndStudentClassIdAndStudentId(LocalDate attDate, Long classId, Long studentId);
//Optional<StudentAttendance> findByAttDateAndStudentClass_IdAndStudent_Id(LocalDate attDate, Long classId, Long studentId);
//Optional<StudentAttendance> findByAtt_DateAndStudentClass_IdAndStudent_Id(LocalDate attDate, Long classId, Long studentId);
    @Query("SELECT s FROM StudentAttendance s WHERE s.att_date = :attDate AND s.studentClass.id = :classId AND s.student.id = :studentId")
    List<StudentAttendance> findByConditions(@Param("attDate") LocalDate attDate, @Param("classId") Long classId, @Param("studentId") Long studentId);
}
