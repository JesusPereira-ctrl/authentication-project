package org.jesus.authproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jesus.authproject.dtos.request.LoginRequest;
import org.jesus.authproject.dtos.request.RegisterRequest;
import org.jesus.authproject.dtos.response.AuthResponse;
import org.jesus.authproject.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
