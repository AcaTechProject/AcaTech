package com.example.heeyoon_hw_2.Repository;

import com.example.heeyoon_hw_2.Domain.NewStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
public interface NewConsultingRepository extends JpaRepository<NewStudent,Long> {

    List<NewStudent> findAllById(Long id);

}
