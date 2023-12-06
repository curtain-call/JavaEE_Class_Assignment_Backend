package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.CheckinDto;
import com.javaeeAssignment.ai_coach_backend.service.CheckinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @ApiOperation("创建新打卡记录")
    @PostMapping
    public ResponseEntity<CheckinDto> createCheckin(@RequestBody CheckinDto checkinDto) {
        CheckinDto createdCheckin = checkinService.createCheckin(checkinDto);
        return ResponseEntity.ok(createdCheckin);
    }

    @ApiOperation("获取单个打卡记录")
    @GetMapping("/{id}")
    public ResponseEntity<CheckinDto> getCheckin(@PathVariable Long id) {
        CheckinDto checkinDto = checkinService.getCheckin(id);
        return ResponseEntity.ok(checkinDto);
    }

    @ApiOperation("获取所有打卡记录")
    @GetMapping
    public ResponseEntity<List<CheckinDto>> getAllCheckins() {
        List<CheckinDto> checkins = checkinService.getAllCheckins();
        return ResponseEntity.ok(checkins);
    }

    @ApiOperation("更新打卡记录")
    @PutMapping("/{id}")
    public ResponseEntity<CheckinDto> updateCheckin(@PathVariable Long id, @RequestBody CheckinDto checkinDto) {
        CheckinDto updatedCheckin = checkinService.updateCheckin(id, checkinDto);
        return ResponseEntity.ok(updatedCheckin);
    }

    @ApiOperation("删除打卡记录")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCheckin(@PathVariable Long id) {
        checkinService.deleteCheckin(id);
        return ResponseEntity.ok().build();
    }
}
