package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.CheckinDto;
import com.javaeeAssignment.ai_coach_backend.model.Checkin;
import com.javaeeAssignment.ai_coach_backend.repository.CheckinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckinService {
    @Autowired
    private CheckinRepository checkinRepository;

    // 创建打卡记录
    public CheckinDto createCheckin(CheckinDto checkinDto) {
        Checkin checkin = convertToEntity(checkinDto);
        checkin = checkinRepository.save(checkin);
        return convertToDto(checkin);
    }

    // 获取特定ID的打卡记录
    public CheckinDto getCheckin(Long id) {
        Checkin checkin = checkinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Checkin not found with id: " + id));
        return convertToDto(checkin);
    }

    // 获取所有打卡记录
    public List<CheckinDto> getAllCheckins() {
        return checkinRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 更新特定ID的打卡记录
    public CheckinDto updateCheckin(Long id, CheckinDto checkinDto) {
        Checkin checkin = checkinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Checkin not found with id: " + id));
        updateEntityWithDto(checkin, checkinDto);
        checkin = checkinRepository.save(checkin);
        return convertToDto(checkin);
    }

    // 删除特定ID的打卡记录
    public void deleteCheckin(Long id) {
        if (!checkinRepository.existsById(id)) {
            throw new EntityNotFoundException("Checkin not found with id: " + id);
        }
        checkinRepository.deleteById(id);
    }

    // 将DTO转换为实体
    private Checkin convertToEntity(CheckinDto dto) {
        Checkin checkin = new Checkin();
        checkin.setDate(dto.getDate());
        checkin.setNotes(dto.getNotes());
        return checkin;
    }

    // 将实体转换为DTO
    private CheckinDto convertToDto(Checkin checkin) {
        CheckinDto dto = new CheckinDto();
        dto.setId(checkin.getId());
        dto.setDate(checkin.getDate());
        dto.setNotes(checkin.getNotes());
        return dto;
    }

    // 从DTO更新实体
    private void updateEntityWithDto(Checkin checkin, CheckinDto dto) {
        checkin.setDate(dto.getDate());
        checkin.setNotes(dto.getNotes());
    }
}
