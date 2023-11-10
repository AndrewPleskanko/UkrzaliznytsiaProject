package com.example.demo.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpRequest {

    @NotBlank
    @Size(min = 3, max = 15, message = "Invalid first name")
    String username;

    @NotBlank
    @Size(min = 5, max = 25, message = "Invalid last name")
    String lastName;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(max = 50, min = 8, message = "Invalid password")
    String password;

    @NotBlank
    String confirmPassword;

}

