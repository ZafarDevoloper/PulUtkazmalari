package com.example.moneytransfer.service;

import com.example.moneytransfer.entity.User;
import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.UserDto;
import com.example.moneytransfer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ApiResponse create(UserDto userDto) {

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());
        if (existsByUsername) {
            return new ApiResponse("Such an username already exists in the system", false);
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return new ApiResponse("User created", true);
    }

    public ApiResponse get() {

        List<User> users = userRepository.findAll();
        return new ApiResponse("Success", true, users);
    }

    public ApiResponse getById(Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        return new ApiResponse("Success", true, optionalUser);
    }

    public ApiResponse edit(Integer userId, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }

        boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());
        if (existsByUsername) {
            return new ApiResponse("Such an username already exists in the system", false);
        }

        User editingUser = optionalUser.get();
        editingUser.setFirstName(userDto.getFirstName());
        editingUser.setLastName(userDto.getLastName());
        editingUser.setUsername(userDto.getUsername());
        editingUser.setPassword(userDto.getPassword());
        userRepository.save(editingUser);

        return new ApiResponse("User edited", true);
    }

    public ApiResponse delete(Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        userRepository.deleteById(userId);
        return new ApiResponse("User deleted", true);
    }
}
