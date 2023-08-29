package com.backend.AcaTech.Repository.NewConsulting;

import com.backend.AcaTech.Domain.Consulting.NewStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewConsultingRepository extends JpaRepository<NewStudent,Long> {

    List<NewStudent> findAllById(Long id);

}