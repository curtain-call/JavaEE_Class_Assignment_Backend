package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDto;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;
import com.javaeeAssignment.ai_coach_backend.repository.TrainingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingPlanService {
    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private ModelMapper modelMapper;

    public TrainingPlanDto createTrainingPlan(TrainingPlanDto trainingPlanDTO) {
        TrainingPlan trainingPlan = modelMapper.map(trainingPlanDTO, TrainingPlan.class);
        TrainingPlan savedPlan = trainingPlanRepository.save(trainingPlan);
        return modelMapper.map(savedPlan, TrainingPlanDto.class);
    }

    public TrainingPlanDto getTrainingPlan(Long id) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training plan not found"));
        return modelMapper.map(trainingPlan, TrainingPlanDto.class);
    }

    public List<TrainingPlanDto> getAllTrainingPlans() {
        return trainingPlanRepository.findAll().stream()
                .map(plan -> modelMapper.map(plan, TrainingPlanDto.class))
                .collect(Collectors.toList());
    }

    public TrainingPlanDto updateTrainingPlan(Long id, TrainingPlanDto trainingPlanDTO) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training plan not found"));
        modelMapper.map(trainingPlanDTO, trainingPlan);
        TrainingPlan updatedPlan = trainingPlanRepository.save(trainingPlan);
        return modelMapper.map(updatedPlan, TrainingPlanDto.class);
    }

    public void deleteTrainingPlan(Long id) {
        trainingPlanRepository.deleteById(id);
    }
}
