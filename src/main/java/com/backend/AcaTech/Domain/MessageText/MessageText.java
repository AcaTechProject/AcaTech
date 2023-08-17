package com.backend.AcaTech.Domain.MessageText;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "message_text")
@Setter
public class MessageText {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mt_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "mt_type", nullable = false)
    private String mt_type;


    @Column(name = "mt_text", nullable = false)
    private String mt_text;

    @Builder
    public MessageText(String mt_type, String mt_text) {
        this.mt_type = mt_type;
        this.mt_text = mt_text;
    }

}
