package com.laxmisri6.employee_management_system.auth;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final JwtService jwtService;

    public UserService(UserRepository repository,
                       JwtService jwtService) {

        this.repository = repository;
        this.jwtService = jwtService;
    }

    public User register(User user) {

        return repository.save(user);
    }

    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return "Invalid password";
        }

        return jwtService.generateToken(user.getEmail());
    }
}