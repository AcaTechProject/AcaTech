package com.backend.AcaTech.Repository;


import com.backend.AcaTech.Domain.ClassName;
import com.backend.AcaTech.Domain.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassNameRepository extends JpaRepository<ClassName, Long> {
    List<ClassName> findAllByOrderByIdDesc();
}
