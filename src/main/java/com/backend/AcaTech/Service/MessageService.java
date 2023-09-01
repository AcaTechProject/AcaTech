package com.backend.AcaTech.Service;


import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Message.Message;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentAttendance;
import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageListResponseDto;
import com.backend.AcaTech.Dto.MessageText.MessageTextListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceListResponseDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Repository.Message.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // 메시지 등록
    @Transactional
    public Long create(MessageCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Message message = requestDto.toEntity();
        message.setUser(user);

        return messageRepository.save(message).getId();
    }

    // 메시지 전체 조회(해당 사용자만)
    public List<MessageListResponseDto> getMessagesByUserId(Long userId) {
        // userId로 해당 유저가 보낸 메시지 목록을 조회하는 로직을 작성
        List<Message> messages = messageRepository.findByUserId(userId);
        return messages.stream()
                .map(MessageListResponseDto::new)
                .collect(Collectors.toList());
    }
}

