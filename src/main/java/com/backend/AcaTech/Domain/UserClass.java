package com.backend.AcaTech.Domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_class")
@Setter
public class UserClass {

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = ClassName.class)
    @JoinColumn(name = "class_id", updatable = false)
    private ClassName user_class;
}
