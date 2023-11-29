package com.javaeeAssignment.ai_coach_backend.dto;

public class TrainingPlanDto {
    //训练计划的id，名称和描述
    private Long id;
    private String name;
    private String description;

    //改训练计划属于哪个用户，通过userId关联
    private Long userId;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
