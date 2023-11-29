package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.CheckinDto;
import com.javaeeAssignment.ai_coach_backend.dto.GoalDto;
import com.javaeeAssignment.ai_coach_backend.service.GoalAndCheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}")
public class GoalAndCheckinController {

    @Autowired
    private GoalAndCheckinService goalAndCheckinService;

    @PostMapping("/goals")
    public ResponseEntity<String> setGoal(@PathVariable Long userId, @RequestBody GoalDto goalDto) {
        goalAndCheckinService.setGoal(userId, goalDto);
        return ResponseEntity.ok("Goal set successfully");
    }

    @PostMapping("/checkin")
    public ResponseEntity<String> checkIn(@PathVariable Long userId, @RequestBody CheckinDto checkinDto) {
        goalAndCheckinService.checkIn(userId, checkinDto);
        return ResponseEntity.ok("Check-in recorded");
    }
}
