package com.javaeeAssignment.ai_coach_backend.controller;

import com.javaeeAssignment.ai_coach_backend.dto.UserDTO;
import com.javaeeAssignment.ai_coach_backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO newUser = userService.register(userDTO);
        return ResponseEntity.ok().body(newUser);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDTO) {
        UserDTO authenticatedUser = userService.login(userDTO);
        if (authenticatedUser != null) {
            return ResponseEntity.ok().body(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @ApiOperation("根据account获取用户")
    @GetMapping("/{account}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String account) {
        UserDTO userDTO = userService.getUserByAccount(account);
        return ResponseEntity.ok().body(userDTO);
    }

    @ApiOperation("根据account更新用户信息")
    @PutMapping("/{account}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String account, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(account, userDTO);
        return ResponseEntity.ok().body(updatedUser);
    }

    @ApiOperation("根据account删除用户")
    @DeleteMapping("/{account}")
    public ResponseEntity<Void> deleteUser(@PathVariable String account) {
        userService.deleteUser(account);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("获取所有用户")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    // 其他可能的用户相关操作
}
