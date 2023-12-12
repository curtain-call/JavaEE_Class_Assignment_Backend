package com.javaeeAssignment.ai_coach_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class SportReport {
    //时长、消耗卡路里、累计天数、最大连续天数、自己上传的userVideo、图片列表、
    // List<String> 问题列表
    // List<String> 建议列表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String account;

    private Double calorie;
    private Long sumDays;
    private Long maxDays;

    private String videoUrl;
    private List<String> imageList;

    private List<String> problemList;
    private List<String> suggestionList;

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

    public Double getCalorie() {
        return calorie;
    }

    public void setCalorie(Double calorie) {
        this.calorie = calorie;
    }

    public Long getSumDays() {
        return sumDays;
    }

    public void setSumDays(Long sumDays) {
        this.sumDays = sumDays;
    }

    public Long getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(Long maxDays) {
        this.maxDays = maxDays;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<String> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<String> problemList) {
        this.problemList = problemList;
    }

    public List<String> getSuggestionList() {
        return suggestionList;
    }

    public void setSuggestionList(List<String> suggestionList) {
        this.suggestionList = suggestionList;
    }
}
