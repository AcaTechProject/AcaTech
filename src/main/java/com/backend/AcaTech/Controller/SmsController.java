package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageListResponseDto;
import com.backend.AcaTech.Dto.Sms.Request;
import com.backend.AcaTech.Dto.Sms.SmsResponse;
import com.backend.AcaTech.Service.MessageService;
import com.backend.AcaTech.Service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;
    private final MessageService messageService;

    // 학생 개별 메시지 전송
    @PostMapping("/student/message/send")
    public ResponseEntity<SmsResponse> test(@RequestBody Request request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException, JsonProcessingException {
        SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }

    // 메시지 저장
    @PostMapping("/student/message/save")
    public Long createBoard(@RequestBody MessageCreateRequestDto requestDto) {
        return messageService.create(requestDto);
    }

    // 메시시 조회
    @GetMapping("/message/{userId}")
    public List<MessageListResponseDto> getMessagesByUserId(@PathVariable Long userId) {
        List<MessageListResponseDto> messages = messageService.getMessagesByUserId(userId);
        return messages;
    }

    // 메시지 다중 삭제
    @DeleteMapping("/message/delete-multiple")
    public void deleteMultipleMessage(@RequestBody List<Long> ids) {
        messageService.deleteMultiple(ids);
    }

}