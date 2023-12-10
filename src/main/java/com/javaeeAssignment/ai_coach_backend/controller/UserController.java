package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.UserDto;
import com.javaeeAssignment.ai_coach_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDTO) {
        UserDto newUser = userService.register(userDTO);
        return ResponseEntity.ok().body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDTO) {
        UserDto authenticatedUser = userService.login(userDTO);
        if (authenticatedUser != null) {
            return ResponseEntity.ok().body(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{account}")
    public ResponseEntity<UserDto> getUser(@PathVariable String account) {
        UserDto userDTO = userService.getUserByAccount(account);
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping("/{account}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String account, @RequestBody UserDto userDTO) {
        UserDto updatedUser = userService.updateUser(account, userDTO);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{account}")
    public ResponseEntity<Void> deleteUser(@PathVariable String account) {
        userService.deleteUser(account);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    // 其他可能的用户相关操作
}
