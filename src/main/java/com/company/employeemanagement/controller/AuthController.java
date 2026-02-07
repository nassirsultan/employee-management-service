package com.company.employeemanagement.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employeemanagement.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        // TEMP: hardcoded user (replace with DB later)
        if (!"admin".equals(request.get("username"))
                || !"admin".equals(request.get("password"))) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken("admin");
        return Map.of("token", token);
    }
}

