package com.javaeeAssignment.ai_coach_backend.dto;

import com.javaeeAssignment.ai_coach_backend.model.BodyData;
import com.javaeeAssignment.ai_coach_backend.model.Motion;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;

import java.util.List;

public class UserDTO {

    private String account;
    private String password;
    private String nickname;

    private String token = null; // 登录时返回的JWT令牌

    // 关注、粉丝、获赞
    private Long followerNum = 0L;
    private Long fanNum = 0L;
    private Long likeNum = 0L;

    private List<BodyData> bodyDataList;

    private List<TrainingPlan> trainingPlanList;

    private List<Motion> motionList;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(Long followerNum) {
        this.followerNum = followerNum;
    }

    public Long getFanNum() {
        return fanNum;
    }

    public void setFanNum(Long fanNum) {
        this.fanNum = fanNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public List<BodyData> getBodyDataList() {
        return bodyDataList;
    }

    public void setBodyDataList(List<BodyData> bodyDataList) {
        this.bodyDataList = bodyDataList;
    }

    public List<TrainingPlan> getTrainingPlanList() {
        return trainingPlanList;
    }

    public void setTrainingPlanList(List<TrainingPlan> trainingPlanList) {
        this.trainingPlanList = trainingPlanList;
    }

    public List<Motion> getMotionList() {
        return motionList;
    }

    public void setMotionList(List<Motion> motionList) {
        this.motionList = motionList;
    }
}