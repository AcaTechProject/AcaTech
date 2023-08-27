package com.backend.AcaTech.Domain.Message;

import com.backend.AcaTech.Domain.Class.User;
import com.backend.AcaTech.Domain.Score.StudentScore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "message")
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mess_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "mess_date", nullable = true)
    private LocalDate mess_date;

    @Column(name = "mess_address", nullable = false)
    private String mess_address;

    @Column(name = "mess_content", nullable = false)
    private String mess_content;

    @Column(name = "mess_result", nullable = false)
    private String mess_result;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Message(User user, LocalDate mess_date, String mess_address, String mess_content, String mess_result) {
        this.user = user;
        this.mess_date = LocalDate.now();
        this.mess_address = mess_address;
        this.mess_content = mess_content;
        this.mess_result = mess_result;
    }
}
