package com.backend.AcaTech.Service;

import com.backend.AcaTech.Dto.MessageText.MessageTextListResponseDto;
import com.backend.AcaTech.Repository.MessageText.MessageTextRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageTextService {
    private final MessageTextRepository messageTextRepository;


    @Transactional
    public List<MessageTextListResponseDto> searchAllDesc() {
        return messageTextRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(MessageTextListResponseDto::new)
                .collect(Collectors.toList());
    }
}