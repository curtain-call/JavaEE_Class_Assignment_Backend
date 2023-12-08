package com.javaeeAssignment.ai_coach_backend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    // account为登陆账号，且为user表primary_key
    @Id
    private String account;
    private String password;
    private String nickname;

    // 关注、粉丝、获赞
    private Long followerNum;
    private Long fanNum;
    private Long likeNum;

    @OneToMany
    private List<BodyData> bodyDataList;

    @OneToMany
    private List<TrainingPlan> trainingPlanList;

    @OneToMany
    private List<Motion> motionList;

    // @OneToMany
    // private List<SportReport> sportReportList;
    //
    // @OneToMany
    // private List<FitnessReport> fitnessReportList;
    //
    // @OneToMany
    // private List<RecommendationList> recommendationListList;

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

    // public List<SportReport> getSportReportList() {
    //     return sportReportList;
    // }
    //
    // public void setSportReportList(List<SportReport> sportReportList) {
    //     this.sportReportList = sportReportList;
    // }
    //
    // public List<FitnessReport> getFitnessReportList() {
    //     return fitnessReportList;
    // }
    //
    // public void setFitnessReportList(List<FitnessReport> fitnessReportList) {
    //     this.fitnessReportList = fitnessReportList;
    // }
    //
    // public List<RecommendationList> getRecommendationListList() {
    //     return recommendationListList;
    // }
    //
    // public void setRecommendationListList(List<RecommendationList> recommendationListList) {
    //     this.recommendationListList = recommendationListList;
    // }
}
