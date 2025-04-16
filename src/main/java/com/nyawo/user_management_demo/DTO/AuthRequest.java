package com.nyawo.user_management_demo.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
