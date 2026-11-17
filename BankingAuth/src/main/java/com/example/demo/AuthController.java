package com.example.demo;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> data) {

        String username = data.get("username");
        String password = data.get("password");

        if ("admin".equals(username) && "1234".equals(password)) {
            return jwtService.generateToken(username);
        }

        return "Invalid Credentials";
    }
}