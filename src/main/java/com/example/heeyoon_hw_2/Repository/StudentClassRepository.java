package com.example.heeyoon_hw_2.Repository;

import com.example.heeyoon_hw_2.Domain.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {

    // 클래스 ID를 기반으로 학생 목록 조회
    List<StudentClass> findByCourse(Long classId);
}