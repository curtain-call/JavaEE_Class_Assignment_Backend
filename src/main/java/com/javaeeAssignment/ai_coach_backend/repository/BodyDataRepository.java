package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.BodyData;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BodyDataRepository extends JpaRepository<BodyData, Long> {
    List<BodyData> findByAccount(String account);
    Optional<BodyData> findByIdAndAccount(Long id, String account);
}