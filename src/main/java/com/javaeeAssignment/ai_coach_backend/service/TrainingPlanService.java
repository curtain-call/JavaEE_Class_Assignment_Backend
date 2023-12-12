package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDTO;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;
import com.javaeeAssignment.ai_coach_backend.model.User;
import com.javaeeAssignment.ai_coach_backend.repository.TrainingPlanRepository;
import com.javaeeAssignment.ai_coach_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public TrainingPlanDTO createTrainingPlan(TrainingPlanDTO trainingPlanDto) {
        User user = userRepository.findByAccount(trainingPlanDto.getAccount());
        if (user == null) {
            throw new IllegalStateException("Invalid account in creating trainingplan");
        }
        TrainingPlan trainingPlan = modelMapper.map(trainingPlanDto, TrainingPlan.class);
        TrainingPlan savedPlan = trainingPlanRepository.save(trainingPlan);
        return modelMapper.map(savedPlan, TrainingPlanDTO.class);
    }

    public TrainingPlanDTO getTrainingPlan(Long id) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training plan not found"));
        return modelMapper.map(trainingPlan, TrainingPlanDTO.class);
    }

    public List<TrainingPlanDTO> getTrainingPlansByAccount(String userAccount) {
        // 只返回与用户账号相关的训练计划
        return trainingPlanRepository.findByAccount(userAccount).stream()
                .map(trainingPlan -> modelMapper.map(trainingPlan, TrainingPlanDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public TrainingPlanDTO updateTrainingPlan(Long id, TrainingPlanDTO trainingPlanDto) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training plan not found"));
        modelMapper.map(trainingPlanDto, trainingPlan);
        TrainingPlan updatedPlan = trainingPlanRepository.save(trainingPlan);
        return modelMapper.map(updatedPlan, TrainingPlanDTO.class);
    }

    @Transactional
    public void deleteTrainingPlan(Long id) {
        trainingPlanRepository.deleteById(id);
    }
}
