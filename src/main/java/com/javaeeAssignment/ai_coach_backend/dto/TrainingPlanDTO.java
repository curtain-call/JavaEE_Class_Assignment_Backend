package com.javaeeAssignment.ai_coach_backend.dto;

import com.javaeeAssignment.ai_coach_backend.model.Motion;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TrainingPlanDTO {

    private Long id = 0L;

    @NotNull
    private String account;

    //训练计划的起始终止时间，应为TimeStamp
    private Double startTime = 0D;
    private Double endTime = 0D;

    //训练计划单独的motion列表，应包含于user中的motionlist
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
