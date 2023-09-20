package com.backend.AcaTech.Repository.Class;


import com.backend.AcaTech.Domain.Class.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassNameRepository extends JpaRepository<CourseInfo, Long> {
    List<CourseInfo> findAllByOrderByIdDesc();
    CourseInfo findByClassName(String className); // 변경된 메서드명
}