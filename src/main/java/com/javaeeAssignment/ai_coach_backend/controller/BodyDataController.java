package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.BodyDataDTO;
import com.javaeeAssignment.ai_coach_backend.service.BodyDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bodydata")
@CrossOrigin
public class BodyDataController {
    @Autowired
    private BodyDataService bodyDataService;

    @ApiOperation("创建/更新身体数据")
    @PostMapping
    public ResponseEntity<BodyDataDTO> createOrUpdateBodyData(@RequestBody BodyDataDTO bodyDataDTO) {
        BodyDataDTO savedBodyData = bodyDataService.createOrUpdateBodyData(bodyDataDTO);
        return ResponseEntity.ok(savedBodyData);
    }

    @ApiOperation("根据id+account获取身体数据")
    @GetMapping("/{id}")
    public ResponseEntity<BodyDataDTO> getBodyData(@PathVariable Long id, @RequestParam String account) {
        BodyDataDTO bodyDataDTO = bodyDataService.getBodyData(id, account);
        return ResponseEntity.ok(bodyDataDTO);
    }

    @ApiOperation("根据account获取所有身体数据")
    @GetMapping("/{account}")
    public ResponseEntity<List<BodyDataDTO>> getAllBodyDataForAccount(@RequestParam String account) {
        List<BodyDataDTO> bodyDataList = bodyDataService.getAllBodyDataForAccount(account);
        return ResponseEntity.ok(bodyDataList);
    }

    @ApiOperation("根据id+account删除身体数据")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBodyData(@PathVariable Long id, @RequestParam String account) {
        bodyDataService.deleteBodyData(id, account);
        return ResponseEntity.ok().build();
    }
}
