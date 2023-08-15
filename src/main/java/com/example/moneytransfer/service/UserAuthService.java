package com.example.moneytransfer.service;

import com.example.moneytransfer.entity.User;
import com.example.moneytransfer.payload.ApiResponse;
import com.example.moneytransfer.payload.UserAuthDto;
import com.example.moneytransfer.repository.UserRepository;
import com.example.moneytransfer.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse login(UserAuthDto userAuthDto) {
        try {
            Optional<User> optUser = userRepository.findByUsername(userAuthDto.getUsername());
            if (optUser.isEmpty()) {
                return new ApiResponse("Login or Password is incorrect", false);
            }
            User user = optUser.get();
            if (!Objects.equals(user.getPassword(), userAuthDto.getPassword())) {
                return new ApiResponse("Login or Password is incorrect", false);
            }
            String token = jwtProvider.generateToken(userAuthDto.getUsername());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Login or Password is incorrect", false);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }
}
