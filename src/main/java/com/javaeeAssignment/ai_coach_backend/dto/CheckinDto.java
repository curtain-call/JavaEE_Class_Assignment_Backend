package com.javaeeAssignment.ai_coach_backend.dto;

import java.util.Date;

public class CheckinDto {
    private Long id;
    private Date date;
    private String notes;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
