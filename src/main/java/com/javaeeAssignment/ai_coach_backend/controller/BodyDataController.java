package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.BodyDataDto;
import com.javaeeAssignment.ai_coach_backend.service.BodyDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/bodydata")
public class BodyDataController {

    @Autowired
    private BodyDataService bodyDataService;

    @ApiOperation("设置身体数据")
    @PostMapping
    public ResponseEntity<String> storeBodyData(@PathVariable Long userId, @RequestBody BodyDataDto bodyDataDto) {
        bodyDataService.storeBodyData(userId, bodyDataDto);
        return ResponseEntity.ok("Body data stored successfully");
    }

    @ApiOperation("获取身体数据")
    @GetMapping
    public ResponseEntity<BodyDataDto> getBodyData(@PathVariable Long userId) {
        BodyDataDto bodyDataDto = bodyDataService.getBodyData(userId);
        return ResponseEntity.ok(bodyDataDto);
    }
}
