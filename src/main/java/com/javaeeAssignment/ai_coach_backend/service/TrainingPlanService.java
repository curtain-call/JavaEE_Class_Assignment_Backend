package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDto;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;
import com.javaeeAssignment.ai_coach_backend.model.User;
import com.javaeeAssignment.ai_coach_backend.repository.TrainingPlanRepository;
import com.javaeeAssignment.ai_coach_backend.repository.UserRepository;
// import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TrainingPlanService {

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;

    @Autowired
    private UserRepository userRepository;

    public TrainingPlanDto createTrainingPlan(TrainingPlanDto dto) {
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


    // Additional methods for update, delete, etc.
}
