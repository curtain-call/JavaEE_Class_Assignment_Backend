package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    List<Checkin> findByUserIdAndDate(Long userId, LocalDate date);
}