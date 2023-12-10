package com.javaeeAssignment.ai_coach_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.File;

@Entity
public class Motion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String name;
    private String description;

    private Boolean status;

    // 标准视频、自传视频、视频封面该用什么文件定义？
    private String standardVideoUrl;
    private String userUploadVideoUrl;
    private String imageUrl;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStandardVideoUrl() {
        return standardVideoUrl;
    }

    public void setStandardVideoUrl(String standardVideoUrl) {
        this.standardVideoUrl = standardVideoUrl;
    }

    public String getUserUploadVideoUrl() {
        return userUploadVideoUrl;
    }

    public void setUserUploadVideoUrl(String userUploadVideoUrl) {
        this.userUploadVideoUrl = userUploadVideoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
