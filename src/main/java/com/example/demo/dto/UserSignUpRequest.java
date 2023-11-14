package com.example.demo.dto;


import com.example.demo.entity.Role;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSignUpRequest {

    @NotBlank
    @Size(min = 3, max = 15, message = "Invalid first name")
    String username;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(max = 50, min = 8, message = "Invalid password")
    String password;

    Set<Role> roleList;
}

