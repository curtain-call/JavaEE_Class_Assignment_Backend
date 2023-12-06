package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
    List<Goal> findByTrainingPlanId(Long trainingPlanId);
}

