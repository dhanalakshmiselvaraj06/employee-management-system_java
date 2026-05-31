package com.laxmisri6.employee_management_system.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}