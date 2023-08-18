package com.backend.AcaTech.Dto.MessageText;

import com.backend.AcaTech.Domain.MessageText.MessageText;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageTextListResponseDto {
    private String mt_type;
    private String mt_text;

    public MessageTextListResponseDto(MessageText entity) {
        this.mt_type = entity.getMt_type();
        this.mt_text = entity.getMt_text();

    }
}