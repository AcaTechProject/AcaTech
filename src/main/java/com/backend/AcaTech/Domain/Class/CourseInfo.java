package com.backend.AcaTech.Domain.Class;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "course")
@Setter
public class CourseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "class_name", nullable = false)
    private String className; // 변경된 변수명

    @Builder
    public CourseInfo(String className) {
        this.className = className;
    }
}