package com.jobportal.backend.controller;

import com.jobportal.backend.model.User;
import com.jobportal.backend.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody User user) {
        User created = authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", created.getId(), "name", created.getName(), "email", created.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request.email(), request.password())
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(Map.of(
                        "message", "Login successful",
                        "user", Map.of("id", user.getId(), "name", user.getName(), "email", user.getEmail())
                )))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Invalid email or password")));
    }

    public record LoginRequest(@Email @NotBlank String email, @NotBlank String password) {}
}
