package com.backend.AcaTech.Dto.Message;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Consulting.Consulting;
import com.backend.AcaTech.Domain.Message.Message;
import com.backend.AcaTech.Domain.Student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
public class MessageCreateRequestDto {
    private User user;
    private LocalDate mess_date;
    private String mess_address;
    private String mess_content;
    private String mess_result;

    public MessageCreateRequestDto(User user, LocalDate mess_date, String mess_address, String mess_content, String mess_result)
    {
        this.user = user;
        this.mess_date = LocalDate.now();
        this.mess_address = mess_address;
        this.mess_content = mess_content;
        this.mess_result = mess_result;
    }

    public Message toEntity() {
        return new Message(user, mess_date, mess_address, mess_content, mess_result);
    }
}


