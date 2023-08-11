package com.backend.AcaTech.Repository;

import com.backend.AcaTech.Domain.User;
import com.backend.AcaTech.Domain.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByIdDesc();
}