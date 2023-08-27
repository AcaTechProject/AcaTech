package com.example.heeyoon_hw_2.Repository;

import com.example.heeyoon_hw_2.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
