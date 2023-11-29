package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.BodyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyDataRepository extends JpaRepository<BodyData, Long> {
    List<BodyData> findByUserId(Long userId);
}