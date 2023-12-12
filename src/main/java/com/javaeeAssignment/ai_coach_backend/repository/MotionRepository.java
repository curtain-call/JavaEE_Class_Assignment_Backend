package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.Motion;
import com.javaeeAssignment.ai_coach_backend.model.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotionRepository extends JpaRepository<Motion, Long> {
    List<Motion> findByAccount(String account);
}
