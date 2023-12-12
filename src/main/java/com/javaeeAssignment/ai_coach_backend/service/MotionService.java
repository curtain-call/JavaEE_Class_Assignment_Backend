package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.MotionDTO;
import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDTO;
import com.javaeeAssignment.ai_coach_backend.model.Motion;
import com.javaeeAssignment.ai_coach_backend.repository.MotionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotionService {

    @Autowired
    private MotionRepository motionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public MotionDTO createMotion(MultipartFile standardVideoFile,
                               String account, String name, String description) throws IOException {
        // 上传标准视频和用户上传视频
        String standardVideoUrl = uploadVideo(standardVideoFile);

        // 创建 Motion 对象并设置属性
        Motion motion = new Motion();
        motion.setStandardVideoUrl(standardVideoUrl);
        motion.setAccount(account);
        motion.setDescription(description);
        motion.setName(name);

        // 保存 Motion 对象到数据库
        motionRepository.save(motion);
        return modelMapper.map(motion, MotionDTO.class);
    }

    @Transactional
    public String uploadVideo(MultipartFile videoFile) throws IOException {
        // 获取当前运行的工作目录，假设是项目根目录
        String currentWorkingDirectory = System.getProperty("user.dir");

        // 在项目根目录下创建一个名为 "videos" 的文件夹，用于存储上传的视频文件
        String uploadDirectory = currentWorkingDirectory + File.separator + "videos";
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 获取上传文件的原始文件名
        String originalFileName = videoFile.getOriginalFilename();

        // 生成文件保存路径
        String videoPath = uploadDirectory + File.separator + originalFileName;

        if(originalFileName.isEmpty()) {
            throw new IOException("No video selected");
        }

        // 保存视频文件到本地
        File file = new File(videoPath);
        videoFile.transferTo(file);

        return videoPath;
    }

    @Transactional
    public String uploadImage(MultipartFile imageFile) throws IOException {
        // 获取当前运行的工作目录，假设是项目根目录
        String currentWorkingDirectory = System.getProperty("user.dir");

        // 在项目根目录下创建一个名为 "images" 的文件夹，用于存储上传的视频文件
        String uploadDirectory = currentWorkingDirectory + File.separator + "images";
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 获取上传文件的原始文件名
        String originalFileName = imageFile.getOriginalFilename();

        // 生成文件保存路径
        String imagePath = uploadDirectory + File.separator + originalFileName;

        if(imagePath.equals(uploadDirectory)) {
            throw new IOException("No image selected");
        }

        // 保存视频文件到本地
        File file = new File(imagePath);
        imageFile.transferTo(file);

        return imagePath;
    }

    public Resource getMotionResource(Long id, String account, String url) throws Exception {
        // 从数据库中检索 Motion 实体
        Motion motion = motionRepository.findById(id).orElseThrow(() -> new Exception("Motion not found"));

        // 确保 account 和 URL 与实体匹配
        if (!motion.getAccount().equals(account) || (!motion.getUserUploadVideoUrl().equals(url) && !motion.getStandardVideoUrl().equals(url))) {
            throw new Exception("Motion details do not match");
        }

        // 假设 url 是文件在服务器上的相对路径
        Path filePath = Paths.get(url);
        if (!Files.exists(filePath)) {
            throw new Exception("File not found");
        }

        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            throw new Exception("File not found");
        }

        return resource;
    }

    public MotionDTO findMotionById(Long id) throws Exception {
        Optional<Motion> motionOptional = motionRepository.findById(id);
        if (!motionOptional.isPresent()) {
            throw new Exception("Motion not found with id: " + id);
        }
        return modelMapper.map(motionOptional.get(), MotionDTO.class);
    }

    public List<MotionDTO> getMotionsByAccount(String account) {
        // 只返回与用户账号相关的Motion
        return motionRepository.findByAccount(account).stream()
                .map(motion -> modelMapper.map(motion, MotionDTO.class))
                .collect(Collectors.toList());
    }

}
