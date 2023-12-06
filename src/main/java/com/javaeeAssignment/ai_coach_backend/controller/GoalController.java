package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.GoalDto;
import com.javaeeAssignment.ai_coach_backend.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // 创建新目标
    @PostMapping
    public ResponseEntity<GoalDto> createGoal(@RequestBody GoalDto goalDto) {
        GoalDto createdGoal = goalService.createGoal(goalDto);
        return ResponseEntity.ok(createdGoal);
    }

    // 获取单个目标
    @GetMapping("/{id}")
    public ResponseEntity<GoalDto> getGoal(@PathVariable Long id) {
        GoalDto goalDto = goalService.getGoal(id);
        return ResponseEntity.ok(goalDto);
    }

    // 获取所有目标
    @GetMapping
    public ResponseEntity<List<GoalDto>> getAllGoals() {
        List<GoalDto> goals = goalService.getAllGoals();
        return ResponseEntity.ok(goals);
    }

    // 更新目标
    @PutMapping("/{id}")
    public ResponseEntity<GoalDto> updateGoal(@PathVariable Long id, @RequestBody GoalDto goalDto) {
        GoalDto updatedGoal = goalService.updateGoal(id, goalDto);
        return ResponseEntity.ok(updatedGoal);
    }

    // 删除目标
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.ok().build();
    }
}
