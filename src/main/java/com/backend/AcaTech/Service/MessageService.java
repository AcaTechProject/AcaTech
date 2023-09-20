package com.backend.AcaTech.Service;


import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Message.Message;
import com.backend.AcaTech.Domain.Student.Student;
import com.backend.AcaTech.Domain.Student.StudentAttendance;
import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageListResponseDto;
import com.backend.AcaTech.Dto.Message.MessageResponseDto;
import com.backend.AcaTech.Dto.MessageText.MessageTextListResponseDto;
import com.backend.AcaTech.Dto.Student.StudentAttendance.StudentAttendanceListResponseDto;
import com.backend.AcaTech.Repository.Class.UserRepository;
import com.backend.AcaTech.Repository.Message.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    // 메시지 등록
// 메시지 등록
    @Transactional
    public MessageResponseDto createMessage(MessageCreateRequestDto requestDto) {
        try {
            validateMessageInput(requestDto);

            User user = userRepository.findById(requestDto.getUser().getId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

            Message message = requestDto.toEntity();
            message.setUser(user);

            Message savedMessage = messageRepository.save(message);

            // 저장된 메시지를 MessageResponseDto로 변환하여 반환
            return new MessageResponseDto(savedMessage);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류: " + e.getMessage(), e);
        }
    }


    private void validateMessageInput(MessageCreateRequestDto requestDto) {
        if (requestDto == null) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }

        if (requestDto.getUser() == null || requestDto.getUser().getId() == null) {
            throw new IllegalArgumentException("유저 정보가 누락되었습니다.");
        }
    }



    // 메시지 전체 조회(해당 사용자만)
    public List<MessageListResponseDto> getMessagesByUserId(Long userId) {
        List<Message> messages = messageRepository.findByUserId(userId);

        List<MessageListResponseDto> messageList = new ArrayList<>();

        for (int i = 0; i < messages.size(); i++) {
            MessageListResponseDto dto = new MessageListResponseDto(messages.get(i));
            dto.setNo((long)(i + 1)); // No 값을 설정
            messageList.add(dto);
        }

        return messageList;
    }

    // 메시지 여러개 삭제
    @Transactional
    public void deleteMultiple(List<Long> ids) {
        List<Message> messages = messageRepository.findByIdIn(ids);
        messageRepository.deleteAll(messages);
    }

}

