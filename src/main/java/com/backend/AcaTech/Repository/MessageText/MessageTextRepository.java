package com.backend.AcaTech.Repository.MessageText;

import com.backend.AcaTech.Domain.Message.MessageText;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTextRepository extends JpaRepository <MessageText, Integer>{
    List<MessageText> findAllByOrderByIdDesc();
}