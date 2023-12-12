package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.BodyData;
import com.javaeeAssignment.ai_coach_backend.model.SportReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SportReportRepository extends JpaRepository<SportReport, Long> {
    List<SportReport> findByAccount(String account);
    Optional<SportReport> findByIdAndAccount(Long id, String account);
}

