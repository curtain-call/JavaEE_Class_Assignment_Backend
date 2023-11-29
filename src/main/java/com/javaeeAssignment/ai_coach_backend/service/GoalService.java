package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.GoalDto;
import com.javaeeAssignment.ai_coach_backend.model.Goal;
import com.javaeeAssignment.ai_coach_backend.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public GoalDto createGoal(GoalDto dto) {
        Goal goal = new Goal();
        goal.setTitle(dto.getTitle());
        goal.setDescription(dto.getDescription());

        Goal savedGoal = goalRepository.save(goal);

        dto.setId(savedGoal.getId());
        return dto;
    }

    // Additional methods for update, delete, etc.
}
