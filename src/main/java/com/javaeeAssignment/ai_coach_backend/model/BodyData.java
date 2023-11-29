package com.javaeeAssignment.ai_coach_backend.model;

import javax.persistence.*;

@Entity
@Table(name = "body_data")
public class BodyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double weight;
    private Double height;

    public void setUserId(Long userId) {this.userId = userId;}

    public void setWeight(Double userWeight) {
        weight = userWeight;
    }

    public void setHeight(Double userHeight) {
        height = userHeight;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}