package com.backend.AcaTech.Repository.Message;

import com.backend.AcaTech.Domain.Message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
