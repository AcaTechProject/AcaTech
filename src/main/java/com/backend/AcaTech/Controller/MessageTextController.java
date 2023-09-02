package com.backend.AcaTech.Controller;


import com.backend.AcaTech.Dto.MessageText.MessageTextListResponseDto;
import com.backend.AcaTech.Service.MessageTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MessageTextController {

    private final MessageTextService messageTextService;


    @GetMapping("/student/message_text")
    public List<MessageTextListResponseDto> searchAllDesc() {
        return messageTextService.searchAllDesc();
    }
}
