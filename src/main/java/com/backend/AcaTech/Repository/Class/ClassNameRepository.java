package com.backend.AcaTech.Repository.Class;


import com.backend.AcaTech.Domain.Class.ClassName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassNameRepository extends JpaRepository<ClassName, Long> {
    List<ClassName> findAllByOrderByIdDesc();
}
