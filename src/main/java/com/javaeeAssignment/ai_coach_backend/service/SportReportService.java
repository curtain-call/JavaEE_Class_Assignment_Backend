package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.SportReportDTO;
import com.javaeeAssignment.ai_coach_backend.model.SportReport;
import com.javaeeAssignment.ai_coach_backend.repository.SportReportRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportReportService{

    @Autowired
    private SportReportRepository sportReportRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SportReportDTO createSportReport(SportReportDTO sportReportDTO) throws IOException {
        SportReport sportReport = modelMapper.map(sportReportDTO, SportReport.class);
        sportReportRepository.save(sportReport);
        return modelMapper.map(sportReport, SportReportDTO.class);
    }

    public SportReportDTO getSportReport(Long id, String account) {
        SportReport sportReport = sportReportRepository.findByIdAndAccount(id, account)
                .orElseThrow(() -> new RuntimeException("SportReport not found"));
        return modelMapper.map(sportReport, SportReportDTO.class);
    }

    public List<SportReportDTO> getAllSportReportForAccount(String account) {
        List<SportReport> sportReportList = sportReportRepository.findByAccount(account);
        return sportReportList.stream()
                .map(sportReport -> modelMapper.map(sportReport, SportReportDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSportReport(Long id, String account) {
        SportReport sportReport = sportReportRepository.findByIdAndAccount(id, account)
                .orElseThrow(() -> new RuntimeException("SportReport not found"));
        sportReportRepository.delete(sportReport);
    }

}
