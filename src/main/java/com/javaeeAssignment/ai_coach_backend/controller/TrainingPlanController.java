package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDto;
import com.javaeeAssignment.ai_coach_backend.service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingplans")
public class TrainingPlanController {
    @Autowired
    private TrainingPlanService trainingPlanService;

    @PostMapping
    public ResponseEntity<TrainingPlanDto> createTrainingPlan(@RequestBody TrainingPlanDto trainingPlanDTO) {
        TrainingPlanDto createdPlan = trainingPlanService.createTrainingPlan(trainingPlanDTO);
        return ResponseEntity.ok(createdPlan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> getTrainingPlan(@PathVariable Long id) {
        TrainingPlanDto trainingPlanDTO = trainingPlanService.getTrainingPlan(id);
        return ResponseEntity.ok(trainingPlanDTO);
    }

    @GetMapping
    public ResponseEntity<List<TrainingPlanDto>> getAllTrainingPlans() {
        List<TrainingPlanDto> trainingPlans = trainingPlanService.getAllTrainingPlans();
        return ResponseEntity.ok(trainingPlans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> updateTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlanDto trainingPlanDTO) {
        TrainingPlanDto updatedPlan = trainingPlanService.updateTrainingPlan(id, trainingPlanDTO);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable Long id) {
        trainingPlanService.deleteTrainingPlan(id);
        return ResponseEntity.ok().build();
    }
}
