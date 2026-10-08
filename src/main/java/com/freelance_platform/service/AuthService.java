package com.freelance_platform.service;

import com.freelance_platform.dto.LoginRequest;
import com.freelance_platform.dto.LoginResponse;
import com.freelance_platform.entity.User;
import com.freelance_platform.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        /*
         * Currently your database contains passwordHash.
         * If you are storing plain password temporarily,
         * compare it directly.
         */
        if (!user.getPasswordHash().equals(request.getPassword())) {

            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole().toString()
        );
    }
}