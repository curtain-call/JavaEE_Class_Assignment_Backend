package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.GoalDto;
import com.javaeeAssignment.ai_coach_backend.model.Goal;
import com.javaeeAssignment.ai_coach_backend.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    // 创建目标
    public GoalDto createGoal(GoalDto goalDto) {
        Goal goal = convertToEntity(goalDto);
        goal = goalRepository.save(goal);
        return convertToDto(goal);
    }

    // 获取特定ID的目标
    public GoalDto getGoal(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        return convertToDto(goal);
    }

    // 获取所有目标
    public List<GoalDto> getAllGoals() {
        return goalRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 更新特定ID的目标
    public GoalDto updateGoal(Long id, GoalDto goalDto) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        updateEntityWithDto(goal, goalDto);
        goal = goalRepository.save(goal);
        return convertToDto(goal);
    }

    // 删除特定ID的目标
    public void deleteGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new EntityNotFoundException("Goal not found with id: " + id);
        }
        goalRepository.deleteById(id);
    }

    // 将DTO转换为实体
    private Goal convertToEntity(GoalDto dto) {
        Goal goal = new Goal();
        goal.setTitle(dto.getTitle());
        goal.setDescription(dto.getDescription());
        return goal;
    }

    // 将实体转换为DTO
    private GoalDto convertToDto(Goal goal) {
        GoalDto dto = new GoalDto();
        dto.setId(goal.getId());
        dto.setTitle(goal.getTitle());
        dto.setDescription(goal.getDescription());
        return dto;
    }

    // 从DTO更新实体
    private void updateEntityWithDto(Goal goal, GoalDto dto) {
        goal.setTitle(dto.getTitle());
        goal.setDescription(dto.getDescription());
    }
}

