package com.lms.service;

import com.lms.dto.LoginRequest;
import com.lms.dto.SignUpRequest;
import com.lms.model.User;
import com.lms.repository.UserRepository;
import com.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole()); // Must be of type enum Role

        userRepository.save(user);

        // ✅ Include role in the JWT token
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    public String loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // ✅ Include role in the JWT token
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }
}
