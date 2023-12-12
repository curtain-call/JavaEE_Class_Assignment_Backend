package com.javaeeAssignment.ai_coach_backend.service;

import com.javaeeAssignment.ai_coach_backend.dto.TrainingPlanDTO;
import com.javaeeAssignment.ai_coach_backend.dto.UserDTO;
import com.javaeeAssignment.ai_coach_backend.model.User;
import com.javaeeAssignment.ai_coach_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserDTO register(UserDTO userDto) {
        if (userRepository.findByAccount(userDto.getAccount()) != null) {
            throw new IllegalStateException("Account already exists");
        }
        User user = new User();
        user.setAccount(userDto.getAccount());
        user.setPassword(userDto.getPassword()); // 加密密码
        user.setNickname(userDto.getNickname());

        user.setFollowerNum(userDto.getFollowerNum() != null ? userDto.getFollowerNum() : 0);
        user.setFanNum(userDto.getFanNum() != null ? userDto.getFanNum() : 0);
        user.setLikeNum(userDto.getLikeNum() != null ? userDto.getLikeNum() : 0);

        user.setBodyDataList(userDto.getBodyDataList() != null ? userDto.getBodyDataList() : new ArrayList<>());
        user.setTrainingPlanList(userDto.getTrainingPlanList() != null ? userDto.getTrainingPlanList() : new ArrayList<>());
        user.setMotionList(userDto.getMotionList() != null ? userDto.getMotionList() : new ArrayList<>());
        
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO login(UserDTO userDto) {
        User user = userRepository.findByAccount(userDto.getAccount());
        if (user != null && userDto.getPassword().equals(user.getPassword())) {
            return modelMapper.map(user, UserDTO.class);
        }
        throw new IllegalStateException("Invalid login credentials");
    }

    public UserDTO getUserByAccount(String account) {
        User user = userRepository.findByAccount(account);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public UserDTO updateUser(String account, UserDTO userDto) {
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

    @Transactional
    public void deleteUser(String account) {
        userRepository.deleteByAccount(account);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setAccount(user.getAccount());
        // 设置其他属性，但排除密码
        return userDto;
    }
}
