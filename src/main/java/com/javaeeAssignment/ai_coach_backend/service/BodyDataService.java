package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.BodyDataDto;
import com.javaeeAssignment.ai_coach_backend.model.BodyData;
import com.javaeeAssignment.ai_coach_backend.repository.BodyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyDataService {

    @Autowired
    private BodyDataRepository bodyDataRepository;

    public void storeBodyData(Long userId, BodyDataDto bodyDataDto) {
        // 实现存储身体数据逻辑
        BodyData bodyData = new BodyData();
        bodyData.setUserId(userId);
        bodyData.setWeight(bodyDataDto.getWeight());
        bodyData.setHeight(bodyDataDto.getHeight());
        bodyDataRepository.save(bodyData);
    }

    public BodyDataDto getBodyData(Long userId) {
        // 实现获取身体数据逻辑
        List<BodyData> bodyDataList = bodyDataRepository.findByUserId(userId);
        // 这里可能需要处理多个数据的情况
        if (!bodyDataList.isEmpty()) {
            BodyData bodyData = bodyDataList.get(0);
            BodyDataDto bodyDataDto = new BodyDataDto();
            bodyDataDto.setWeight(bodyData.getWeight());
            bodyDataDto.setHeight(bodyData.getHeight());
            return bodyDataDto;
        }
        return null;
    }
}
