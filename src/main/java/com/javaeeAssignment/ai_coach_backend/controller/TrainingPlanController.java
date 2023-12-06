package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDto;
import com.javaeeAssignment.ai_coach_backend.service.TrainingPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-plans")
public class TrainingPlanController {

    @Autowired
    private TrainingPlanService trainingPlanService;

    @ApiOperation("创建训练计划")
    @PostMapping
    public ResponseEntity<TrainingPlanDto> createTrainingPlan(@RequestBody TrainingPlanDto trainingPlanDto) {
        TrainingPlanDto createdPlan = trainingPlanService.createTrainingPlan(trainingPlanDto);
        return ResponseEntity.ok(createdPlan);
    }

    @ApiOperation("根据ID获取训练计划")
    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> getTrainingPlan(@PathVariable Long id) {
        TrainingPlanDto trainingPlanDto = trainingPlanService.getTrainingPlanById(id);
        return ResponseEntity.ok(trainingPlanDto);
    }

    @ApiOperation("更新训练计划")
    @PutMapping("/{id}")
    public ResponseEntity<TrainingPlanDto> updateTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlanDto trainingPlanDto) {
        TrainingPlanDto updatedPlan = trainingPlanService.updateTrainingPlan(id, trainingPlanDto);
        return ResponseEntity.ok(updatedPlan);
    }

    @ApiOperation("删除训练计划")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable Long id) {
        trainingPlanService.deleteTrainingPlan(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("根据userId获取用户训练计划列表")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TrainingPlanDto>> getTrainingPlansByUserId(@PathVariable Long userId) {
        List<TrainingPlanDto> trainingPlans = trainingPlanService.getTrainingPlansByUserId(userId);
        return ResponseEntity.ok(trainingPlans);
    }
}



