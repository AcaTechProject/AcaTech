package com.backend.AcaTech.Controller;

import com.backend.AcaTech.Dto.Consulting.ConsultingCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageCreateRequestDto;
import com.backend.AcaTech.Dto.Message.MessageListResponseDto;
import com.backend.AcaTech.Dto.Message.MessageResponseDto;
import com.backend.AcaTech.Dto.Response.ResponseMessage;
import com.backend.AcaTech.Dto.Sms.Request;
import com.backend.AcaTech.Dto.Sms.SmsRequest;
import com.backend.AcaTech.Dto.Sms.SmsResponse;
import com.backend.AcaTech.Service.MessageService;
import com.backend.AcaTech.Service.SmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
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


    // 다중 메시지 전송
    @PostMapping("/students/message/multi-send")
    public ResponseEntity<List<SmsResponse>> sendMessagesToStudents(@RequestBody List<Request> requests) {
        List<SmsResponse> responses = new ArrayList<>();
        for (Request request : requests) {
            try {
                SmsResponse response = smsService.sendSmsMany(Collections.singletonList(request.getRecipientPhoneNumber()), request.getContent());
                responses.add(response);
            } catch (Exception e) {

                responses.add(null); // 실패한 경우 null 또는 다른 특정 값으로 표시할 수 있습니다.
            }
        }
        return ResponseEntity.ok().body(responses);
    }


    // 메시지 저장
    @PostMapping("/student/message/save")
    public ResponseEntity<ResponseMessage> createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        try {
            MessageResponseDto messageId = messageService.createMessage(requestDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(true, "메시지 저장 성공", messageId));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage<>(false, ex.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage<>(false, "서버 오류: " + e.getMessage(), null));
        }
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