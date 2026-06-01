package com.laxmisri6.employee_management_system.auth;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {

        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return service.register(user);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ) {

        return "Login successful";
    }
}