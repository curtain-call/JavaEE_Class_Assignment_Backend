package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDTO;
import com.javaeeAssignment.ai_coach_backend.service.TrainingPlanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingplans")
@CrossOrigin
public class TrainingPlanController {
    @Autowired
    private TrainingPlanService trainingPlanService;

    @ApiOperation("创建训练计划")
    @PostMapping
    public ResponseEntity<TrainingPlanDTO> createTrainingPlan(@RequestBody TrainingPlanDTO trainingPlanDto) {
        TrainingPlanDTO createdPlan = trainingPlanService.createTrainingPlan(trainingPlanDto);
        return ResponseEntity.ok(createdPlan);
    }

    @ApiOperation("根据id获取训练计划")
    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDTO> getTrainingPlan(@PathVariable Long id) {
        TrainingPlanDTO trainingPlanDto = trainingPlanService.getTrainingPlan(id);
        return ResponseEntity.ok(trainingPlanDto);
    }

    @ApiOperation("根据account获取用户训练计划列表")
    @GetMapping
    public ResponseEntity<List<TrainingPlanDTO>> getTrainingPlansByAccount(@PathVariable String account) {
        List<TrainingPlanDTO> plans = trainingPlanService.getTrainingPlansByAccount(account);
        return ResponseEntity.ok(plans);
    }

    @ApiOperation("根据id和dto更新训练计划")
    @PutMapping("/{id}")
    public ResponseEntity<TrainingPlanDTO> updateTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlanDTO trainingPlanDto) {
        TrainingPlanDTO updatedPlan = trainingPlanService.updateTrainingPlan(id, trainingPlanDto);
        return ResponseEntity.ok(updatedPlan);
    }

    @ApiOperation("根据id删除训练计划")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingPlan(@PathVariable Long id) {
        trainingPlanService.deleteTrainingPlan(id);
        return ResponseEntity.ok().build();
    }
}
