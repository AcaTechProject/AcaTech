package com.backend.AcaTech.Domain.Class;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "class")
@Setter
public class ClassName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", unique = true, nullable = false)
    private Long id;

//    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
//    @JoinColumn(name = "user_id", updatable = false)
//    private User user;
//
    @Column(name = "class_name", nullable = false)
    private String class_name;

    @Builder
    public ClassName(String class_name) {
        this.class_name = class_name;
    }

}
