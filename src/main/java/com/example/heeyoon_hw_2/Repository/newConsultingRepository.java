package com.example.heeyoon_hw_2.Repository;

import com.example.heeyoon_hw_2.Domain.newStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
public interface newConsultingRepository extends JpaRepository<newStudent,Long> {

    List<newStudent> findAllById(Long id);

}
