package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.UserDto;
import com.javaeeAssignment.ai_coach_backend.model.User;
import com.javaeeAssignment.ai_coach_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto register(UserDto userDto) {
        if (userRepository.findByAccount(userDto.getAccount()) != null) {
            throw new IllegalStateException("Account already exists");
        }
        User user = new User();
        user.setAccount(userDto.getAccount());
        user.setPassword(userDto.getPassword()); // 加密密码
        // 设置其他属性
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDto login(UserDto userDto) {
        User user = userRepository.findByAccount(userDto.getAccount());
        if (user != null && userDto.getPassword().equals(user.getPassword())) {
            return convertToDTO(user);
        }
        throw new IllegalStateException("Invalid login credentials");
    }

    public UserDto getUserByAccount(String account) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        return convertToDTO(user);
    }

    public UserDto updateUser(String account, UserDto userDto) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        // 更新用户信息
        // 注意：不要覆盖密码，除非也要更新它
        user.setNickname(userDto.getNickname());
        // 设置其他属性
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(String account) {
        userRepository.deleteByAccount(account);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDto convertToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setAccount(user.getAccount());
        // 设置其他属性，但排除密码
        return userDto;
    }
}
