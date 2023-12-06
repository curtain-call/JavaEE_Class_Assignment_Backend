package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.UserDto;
import com.javaeeAssignment.ai_coach_backend.dto.UserLoginDto;
import com.javaeeAssignment.ai_coach_backend.dto.UserRegisterDto;
import com.javaeeAssignment.ai_coach_backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Valid
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto registrationDto) {
        userService.registerUser(registrationDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserLoginDto loginDto) {
        UserDto userDto = userService.loginUser(loginDto);
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

