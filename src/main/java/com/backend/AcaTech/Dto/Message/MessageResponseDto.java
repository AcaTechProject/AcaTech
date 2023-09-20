package com.backend.AcaTech.Dto.Message;


import com.backend.AcaTech.Domain.Message.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponseDto {

    private Long id;
    private Long userId;
    private LocalDate mess_date;
    private String mess_address;
    private String mess_content;
    private String mess_result;

    public MessageResponseDto(Message message) {
        this.id = message.getId();
        this.userId = message.getUser().getId();
        this.mess_date = message.getMess_date();
        this.mess_address = message.getMess_address();
        this.mess_content = message.getMess_content();
        this.mess_result = message.getMess_result();
    }
}