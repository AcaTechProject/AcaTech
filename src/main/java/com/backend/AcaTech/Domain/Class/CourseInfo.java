package com.backend.AcaTech.Domain.Class;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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
    private String className;

    @JsonIgnore
    @ManyToMany(mappedBy = "classes")
    private Set<User> users;

    @Builder
    public CourseInfo(String className) {
        this.className = className;
    }
}