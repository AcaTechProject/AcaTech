package com.backend.AcaTech.Repository.MyPage;

import com.backend.AcaTech.Domain.Class.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyPageRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}