package com.javaeeAssignment.ai_coach_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "training_plan")
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String account;

    /**
     * @pa
     */
    private Double startTime;
    private Double endTime;

    //训练计划单独的motion列表，应包含于user中的motionlist
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "training_plan_motion",
            joinColumns = @JoinColumn(name = "training_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "motion_id")
    )
    private List<Motion> motionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }

    public List<Motion> getMotionList() {
        return motionList;
    }

    public void setMotionList(List<Motion> motionList) {
        this.motionList = motionList;
    }
}
