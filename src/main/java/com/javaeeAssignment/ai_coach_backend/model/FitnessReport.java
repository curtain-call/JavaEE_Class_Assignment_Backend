package com.javaeeAssignment.ai_coach_backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
* report的内容:
*   1. 动作消耗的卡路里
*   2. 做动作时的最大心率
*   3. 过往最好成绩(数量/速度)
*   4. 数据趋势
*   5. 按照当前运动量评估, 大概多久能够达到你的目标
*
* */
public class FitnessReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long maxHeartRate;
    private String grade;



}
