package com.ai.ecommerce.authservice.controller;

import com.ai.ecommerce.authservice.dto.AuthResponse;
import com.ai.ecommerce.authservice.dto.LoginRequest;
import com.ai.ecommerce.authservice.dto.RegisterRequest;
import com.ai.ecommerce.authservice.dto.UserResponse;
import com.ai.ecommerce.authservice.entity.User;
import com.ai.ecommerce.authservice.service.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // ---------------- PUBLIC ENDPOINTS ----------------
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    // ---------------- AUTHENTICATED ENDPOINTS ----------------
    @GetMapping("/me")
    public UserResponse me() {
        User user = getAuthenticatedUser();
        return mapToResponse(user);
    }

    @GetMapping("/profile")
    public UserResponse profile() {
        User user = getAuthenticatedUser();
        return mapToResponse(user);
    }

    // ---------------- ADMIN ONLY ENDPOINT ----------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminOnly() {
        return "Hello Admin! You have access.";
    }

    // ---------------- PRIVATE HELPER METHODS ----------------
    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("Unauthorized");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return authService.getUserByEmail(userDetails.getUsername());
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }
}
