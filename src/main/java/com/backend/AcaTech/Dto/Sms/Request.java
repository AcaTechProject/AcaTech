package com.backend.AcaTech.Dto.Sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

// 수신 정보
public class Request {
    private String recipientPhoneNumber;
    private String title;
    private String content;

}