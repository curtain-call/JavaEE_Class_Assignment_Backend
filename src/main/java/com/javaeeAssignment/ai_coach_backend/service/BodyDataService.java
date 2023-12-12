package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.BodyDataDTO;
import com.javaeeAssignment.ai_coach_backend.model.BodyData;
import com.javaeeAssignment.ai_coach_backend.repository.BodyDataRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BodyDataService {
    @Autowired
    private BodyDataRepository bodyDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    //创建 和 更新 调用同一个service
    @Transactional
    public BodyDataDTO createOrUpdateBodyData(BodyDataDTO bodyDataDTO) {
        BodyData bodyData = modelMapper.map(bodyDataDTO, BodyData.class);
        bodyData = bodyDataRepository.save(bodyData);
        return modelMapper.map(bodyData, BodyDataDTO.class);
    }

    public BodyDataDTO getBodyData(Long id, String account) {
        BodyData bodyData = bodyDataRepository.findByIdAndAccount(id, account)
                .orElseThrow(() -> new RuntimeException("BodyData not found"));
        return modelMapper.map(bodyData, BodyDataDTO.class);
    }

    public List<BodyDataDTO> getAllBodyDataForAccount(String account) {
        List<BodyData> bodyDataList = bodyDataRepository.findByAccount(account);
        return bodyDataList.stream()
                .map(data -> modelMapper.map(data, BodyDataDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteBodyData(Long id, String account) {
        BodyData bodyData = bodyDataRepository.findByIdAndAccount(id, account)
                .orElseThrow(() -> new RuntimeException("BodyData not found"));
        bodyDataRepository.delete(bodyData);
    }
}
