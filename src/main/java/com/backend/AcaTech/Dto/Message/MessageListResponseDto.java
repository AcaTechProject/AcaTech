package com.backend.AcaTech.Dto.Message;


import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Message.Message;
import com.backend.AcaTech.Domain.Message.MessageText;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MessageListResponseDto {

    private Long id;
    private Long userId;
    private LocalDate mess_date;
    private String mess_address;
    private String mess_content;
    private String mess_result;

    public MessageListResponseDto(Message entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.mess_date = entity.getMess_date();
        this.mess_address = entity.getMess_address();
        String originalMessContent = entity.getMess_content();
        if (originalMessContent.length() > 50) {
            this.mess_content = originalMessContent.substring(0, 50) + "...";
        } else {
            this.mess_content = originalMessContent;
        }
        this.mess_result = entity.getMess_result();

    }
}
