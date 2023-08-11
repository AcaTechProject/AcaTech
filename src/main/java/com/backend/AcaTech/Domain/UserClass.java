package com.backend.AcaTech.Domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_class")
@Setter
public class UserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = ClassName.class)
    @JoinColumn(name = "class_id", updatable = false)
    private ClassName className;

    @Builder
    public UserClass(User user,  ClassName className) {
        this.user = user;
        this.className = className;
    }

}
