package com.example.ms.teachers.management.api.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;


    @OneToOne
    @JoinColumn(name = "salary_id", nullable = false)
    private Salary salary;

    @OneToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
}
