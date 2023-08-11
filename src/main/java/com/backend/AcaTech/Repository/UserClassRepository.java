package com.backend.AcaTech.Repository;

import com.backend.AcaTech.Domain.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClassRepository extends JpaRepository<UserClass, Long> {
    List<UserClass> findAllByOrderByIdDesc();
}
