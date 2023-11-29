package com.javaeeAssignment.ai_coach_backend.repository;

import com.javaeeAssignment.ai_coach_backend.model.User;
// import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// @Mapper
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
