package com.example.heeyoon_hw_2.Repository;

import com.example.heeyoon_hw_2.Domain.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<CourseInfo, Long> {
    Optional<CourseInfo> findByCourseName(String courseName);
}
