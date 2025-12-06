package com.ai.ecommerce.authservice.service;

import com.ai.ecommerce.authservice.dto.AuthResponse;
import com.ai.ecommerce.authservice.dto.LoginRequest;
import com.ai.ecommerce.authservice.dto.RegisterRequest;
import com.ai.ecommerce.authservice.entity.User;
import com.ai.ecommerce.authservice.entity.Role;
import com.ai.ecommerce.authservice.entity.RefreshToken;
import com.ai.ecommerce.authservice.repository.UserRepository;
import com.ai.ecommerce.authservice.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email Already Exists!");
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER // default role
        );

        userRepository.save(user);

        return new AuthResponse("Registered User Successfully");
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                    () -> new RuntimeException("User not found")
            );

            String token = jwtUtil.generateToken(user);

            // Create refresh token
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

            return new AuthResponse("Login Successful", token, refreshToken.getToken());

        } catch (AuthenticationException e) {
            return new AuthResponse("Invalid email or password");
        }
    }

    public AuthResponse refreshToken(String refreshTokenStr) {
        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenStr)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();
        String token = jwtUtil.generateToken(user);

        return new AuthResponse("Token Refreshed Successfully", token, refreshTokenStr);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
