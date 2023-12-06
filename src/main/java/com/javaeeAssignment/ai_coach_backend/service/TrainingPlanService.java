package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDto;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;
import com.javaeeAssignment.ai_coach_backend.model.User;
import com.javaeeAssignment.ai_coach_backend.repository.TrainingPlanRepository;
import com.javaeeAssignment.ai_coach_backend.repository.UserRepository;
// import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingPlanService {

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private UserRepository userRepository;


    public TrainingPlanDto createTrainingPlan(TrainingPlanDto dto) {

        if (dto.getEndDate().before(dto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setName(dto.getName());
        trainingPlan.setDescription(dto.getDescription());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + dto.getUserId()));
        trainingPlan.setUser(user);

        TrainingPlan savedPlan = trainingPlanRepository.save(trainingPlan);

        dto.setId(savedPlan.getId());
        return dto;
    }

    public TrainingPlanDto getTrainingPlanById(Long id) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training Plan not found with id " + id));

        TrainingPlanDto dto = new TrainingPlanDto();
        dto.setId(trainingPlan.getId());
        dto.setName(trainingPlan.getName());
        dto.setDescription(trainingPlan.getDescription());

        return dto;
    }


    public TrainingPlanDto updateTrainingPlan(Long id, TrainingPlanDto trainingPlanDto) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Training Plan not found with id " + id));

        trainingPlan.setName(trainingPlanDto.getName());
        trainingPlan.setDescription(trainingPlanDto.getDescription());

        TrainingPlan updatedPlan = trainingPlanRepository.save(trainingPlan);

        trainingPlanDto.setId(updatedPlan.getId());
        return trainingPlanDto;
    }


    public void deleteTrainingPlan(Long id) {
        if (!trainingPlanRepository.existsById(id)) {
            throw new EntityNotFoundException("Training Plan not found with id " + id);
        }
        trainingPlanRepository.deleteById(id);
    }


    public List<TrainingPlanDto> getTrainingPlansByUserId(Long userId) {
        // 从仓库获取训练计划
        List<TrainingPlan> trainingPlans = trainingPlanRepository.findByUserId(userId);

        // 将实体列表转换为DTO列表
        return trainingPlans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TrainingPlanDto convertToDto(TrainingPlan trainingPlan) {
        TrainingPlanDto dto = new TrainingPlanDto();
        dto.setId(trainingPlan.getId());
        dto.setName(trainingPlan.getName());
        dto.setDescription(trainingPlan.getDescription());
        // 根据需要添加其他字段的转换
        return dto;
    }


    // Additional methods for update, delete, etc.
}
