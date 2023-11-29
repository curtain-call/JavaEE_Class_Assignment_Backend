package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.CheckinDto;
import com.javaeeAssignment.ai_coach_backend.dto.GoalDto;
import com.javaeeAssignment.ai_coach_backend.model.Checkin;
import com.javaeeAssignment.ai_coach_backend.model.Goal;
import com.javaeeAssignment.ai_coach_backend.repository.CheckinRepository;
import com.javaeeAssignment.ai_coach_backend.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GoalAndCheckinService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private CheckinRepository checkinRepository;

    public void setGoal(Long userId, GoalDto goalDto) {
        // 实现目标设定逻辑
        Goal goal = new Goal();
        goal.setUserId(userId);
        goal.setDescription(goalDto.getDescription());
        goalRepository.save(goal);
    }

    public void checkIn(Long userId, CheckinDto checkinDto) {
        // 实现每天打卡逻辑
        Checkin checkin = new Checkin();
        checkin.setUserId(userId);
        checkin.setDate(LocalDate.now());
        checkinRepository.save(checkin);
    }
}
