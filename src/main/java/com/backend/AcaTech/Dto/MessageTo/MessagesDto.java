package com.backend.AcaTech.Dto.MessageTo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

// 메시지 수신
public class MessagesDto {
    private String to;
    private String content;
}
