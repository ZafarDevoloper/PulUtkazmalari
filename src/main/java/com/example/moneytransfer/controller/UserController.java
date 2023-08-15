package com.example.moneytransfer.controller;

import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.UserAuthDto;
import com.example.moneytransfer.payload.UserDto;
import com.example.moneytransfer.service.UserAuthService;
import com.example.moneytransfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody UserDto userDto) {

        ApiResponse apiResponse = userService.create(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody UserAuthDto userAuthDto) {

        ApiResponse apiResponse = userAuthService.login(userAuthDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> get() {

        ApiResponse apiResponse = userService.get();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{userId}")
    public HttpEntity<?> getById(@PathVariable Integer userId) {

        ApiResponse apiResponse = userService.getById(userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{userId}")
    public HttpEntity<?> edit(@PathVariable Integer userId, @RequestBody UserDto userDto) {

        ApiResponse apiResponse = userService.edit(userId, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{userId}")
    public HttpEntity<?> delete(@PathVariable Integer userId) {

        ApiResponse apiResponse = userService.delete(userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
