package com.backend.AcaTech.Repository.Class;

import com.backend.AcaTech.Domain.Class.CourseInfo;
import com.backend.AcaTech.Domain.Class.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByIdDesc();
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndAuthKey(String email, String authKey);

    Set<CourseInfo> findClassesById(Long userId);

}