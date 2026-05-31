package com.laxmisri6.employee_management_system.auth;

import org.springframework.web.bind.annotation.*;

@RestController
/*@CrossOrigin(origins = "http://localhost:5173")*/
@RequestMapping("/auth")

public class AuthController {

    private final UserService service;
    private final OtpService otpService;

    public AuthController(UserService service,
                          OtpService otpService) {

        this.service = service;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return service.login(request);
    }
    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {

        String otp = otpService.generateOtp();

        otpService.storeOtp(email, otp);

        otpService.sendOtp(email, otp);

        return "OTP sent successfully";
    }
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest request) {

        boolean valid = otpService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        if (valid) {
            return "OTP verified successfully";
        }

        return "Invalid OTP";
    }
}