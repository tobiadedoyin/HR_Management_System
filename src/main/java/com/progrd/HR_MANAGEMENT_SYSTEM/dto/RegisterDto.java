package com.progrd.HR_MANAGEMENT_SYSTEM.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private  String username;
    private String email;
    private String password;
}
