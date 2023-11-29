package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDto;
import com.javaeeAssignment.ai_coach_backend.service.TrainingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training-plans")
public class TrainingPlanController {

    @Autowired
    private TrainingPlanService trainingPlanService;

    // 创建训练计划
    @PostMapping
    public ResponseEntity<TrainingPlanDto> createTrainingPlan(@RequestBody TrainingPlanDto trainingPlanDto) {
        TrainingPlanDto createdPlan = trainingPlanService.createTrainingPlan(trainingPlanDto);
        return ResponseEntity.ok(createdPlan);
    }

    // 根据ID获取训练计划
    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> getTrainingPlan(@PathVariable Long id) {
        TrainingPlanDto trainingPlanDto = trainingPlanService.getTrainingPlanById(id);
        return ResponseEntity.ok(trainingPlanDto);
    }

    // 更新训练计划
    @PutMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> updateTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlanDto trainingPlanDto) {
        TrainingPlanDto updatedPlan = trainingPlanService.updateTrainingPlan(id, trainingPlanDto);
        return ResponseEntity.ok(updatedPlan);
    }

    // 删除训练计划
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable Long id) {
        trainingPlanService.deleteTrainingPlan(id);
        return ResponseEntity.ok().build();
    }
}



