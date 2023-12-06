package com.javaeeAssignment.ai_coach_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.javaeeAssignment.ai_coach_backend")
@EnableJpaRepositories(basePackages = "com.javaeeAssignment.ai_coach_backend.repository")
public class AiCoachBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCoachBackendApplication.class, args);
    }

}
