package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.MotionDTO;
import com.javaeeAssignment.ai_coach_backend.model.Motion;
import com.javaeeAssignment.ai_coach_backend.service.MotionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/motions")
@CrossOrigin
public class MotionController {

    @Autowired
    private MotionService motionService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/createMotion")
    public ResponseEntity<?> createMotion(
            @RequestParam("standardVideoFile") MultipartFile standardVideoFile,
            @RequestParam("userUploadVideoFile") MultipartFile userUploadVideoFile,
            @RequestParam("standardVideoImageFile") MultipartFile standardVideoImageFile,
            @RequestParam("userUploadVideoImageFile") MultipartFile userUploadVideoImageFile,
            @RequestBody MotionDTO motionDTO) {
        try {
            MotionDTO motionDTO1 = motionService.createMotion(
                    standardVideoFile,
                    userUploadVideoFile,
                    standardVideoImageFile,
                    userUploadVideoImageFile,
                    motionDTO);

            return ResponseEntity.ok(motionDTO1);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create motion");
        }
    }

    @GetMapping("/getMotion")
    public ResponseEntity<MotionDTO> getMotion(@RequestParam Long id) {
        try {
            Motion motion = motionService.findMotionById(id);
            MotionDTO motionDTO = modelMapper.map(motion, MotionDTO.class);
            return ResponseEntity.ok(motionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getMotionResource")
    public ResponseEntity<Resource> getMotionResource(@RequestParam Long id, @RequestParam String account, @RequestParam String url) {
        try {
            Resource resource = motionService.getMotionResource(id, account, url);
            Path filePath = resource.getFile().toPath();

            // 动态确定文件 MIME 类型
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream"; // 默认 MIME 类型
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }

        // try {
        //     Resource videoResource = motionService.getMotionResource(id, account, url);
        //     return ResponseEntity.ok()
        //             .contentType(MediaType.parseMediaType("video/mp4")) // 假设视频都是 MP4 格式，或者需要动态确定 MIME 类型
        //             .body(videoResource);
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return ResponseEntity.badRequest().body(null);
        // }

    }

}
