package com.javaeeAssignment.ai_coach_backend.model;


import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "checkin")
public class Checkin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String notes;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    // Constructors
    public Checkin() {}

    public Checkin(Date date, String notes) {
        this.date = date;
        this.notes = notes;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

