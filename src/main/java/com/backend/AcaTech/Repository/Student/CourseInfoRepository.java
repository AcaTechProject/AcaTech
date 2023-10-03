package com.backend.AcaTech.Repository.Student;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {
    List<CourseInfo> findByClassName(String className);
}
