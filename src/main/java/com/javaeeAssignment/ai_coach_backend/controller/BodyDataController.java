package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.BodyDataDto;
import com.javaeeAssignment.ai_coach_backend.service.BodyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/bodydata")
public class BodyDataController {

    @Autowired
    private BodyDataService bodyDataService;

    @PostMapping
    public ResponseEntity<String> storeBodyData(@PathVariable Long userId, @RequestBody BodyDataDto bodyDataDto) {
        bodyDataService.storeBodyData(userId, bodyDataDto);
        return ResponseEntity.ok("Body data stored successfully");
    }

    @GetMapping
    public ResponseEntity<BodyDataDto> getBodyData(@PathVariable Long userId) {
        BodyDataDto bodyDataDto = bodyDataService.getBodyData(userId);
        return ResponseEntity.ok(bodyDataDto);
    }
}
