package com.javaeeAssignment.ai_coach_backend.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "checkin")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDate date;

    public void setUserId(Long userId) {
    }

    public void setDate(LocalDate now) {
    }
    // other fields, getters, setters
}