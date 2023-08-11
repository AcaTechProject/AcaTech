package com.backend.AcaTech.Repository.Class;

import com.backend.AcaTech.Domain.Class.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClassRepository extends JpaRepository<UserClass, Long> {
    List<UserClass> findAllByOrderByIdDesc();
}
