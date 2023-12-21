package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.SportReportDTO;
import com.javaeeAssignment.ai_coach_backend.service.SportReportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/sportReports")
public class SportReportController {

    @Autowired
    private SportReportService sportReportService;

    @ApiOperation("生成运动报告")
    @PostMapping
    public ResponseEntity<SportReportDTO> createSportReport(@RequestBody SportReportDTO sportReportDTO) throws IOException {
        SportReportDTO newSportReportDTO = sportReportService.createSportReport(sportReportDTO);
        return ResponseEntity.ok(newSportReportDTO);
    }

    @ApiOperation("根据id获取运动报告")
    @GetMapping("/{id}")
    public ResponseEntity<SportReportDTO> getSportReport(@PathVariable Long id, @RequestParam String account) {
        SportReportDTO sportReportDTO = sportReportService.getSportReport(id, account);
        return ResponseEntity.ok(sportReportDTO);
    }

    @ApiOperation("获取用户运动报告列表")
    @GetMapping
    public ResponseEntity<List<SportReportDTO>> getAllSportReportsForAccount(@RequestParam String account) {
        List<SportReportDTO> sportReportDTOs = sportReportService.getAllSportReportForAccount(account);
        return ResponseEntity.ok(sportReportDTOs);
    }

    @ApiOperation("删除运动报告")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSportReport(@PathVariable Long id, @RequestParam String account) {
        sportReportService.deleteSportReport(id, account);
        return ResponseEntity.ok().build();
    }

    // 其他必要的端点...
}
