package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.UserDto;
import com.javaeeAssignment.ai_coach_backend.dto.UserLoginDto;
import com.javaeeAssignment.ai_coach_backend.dto.UserRegisterDto;
import com.javaeeAssignment.ai_coach_backend.model.User;
import com.javaeeAssignment.ai_coach_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserRegisterDto registrationDto) {
        // 实现用户注册逻辑
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        userRepository.save(user);
    }

    public UserDto loginUser(UserLoginDto loginDto) {
        // 实现用户登录逻辑
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            return userDto;
        }
        return null;
    }
}
