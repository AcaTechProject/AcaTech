package com.backend.AcaTech.Repository.Message;

import com.backend.AcaTech.Domain.Message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByUserId(Long userId);

    List<Message> findByIdIn(List<Long> ids);
}
