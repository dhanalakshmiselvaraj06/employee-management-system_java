package com.laxmisri6.employee_management_system.auth;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

import java.util.HashMap;
import java.util.Map;


@Service

public class OtpService {

    private final JavaMailSender mailSender;
    private final Map<String, String> otpStorage = new HashMap<>();


    public OtpService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String generateOtp() {

        Random random = new Random();

        int otp = 100000 + random.nextInt(900000);

        return String.valueOf(otp);
    }

    public void storeOtp(String email, String otp) {

        otpStorage.put(email, otp);
    }

    public void sendOtp(String email, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);

        message.setSubject("Your OTP Code");

        message.setText("Your OTP is: " + otp);

        mailSender.send(message);
    }

    public boolean verifyOtp(String email, String otp) {

        String storedOtp = otpStorage.get(email);

        return storedOtp != null && storedOtp.equals(otp);
    }
}