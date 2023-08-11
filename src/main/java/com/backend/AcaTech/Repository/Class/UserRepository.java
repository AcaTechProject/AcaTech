package com.backend.AcaTech.Repository.Class;

import com.backend.AcaTech.Domain.Class.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByIdDesc();
}