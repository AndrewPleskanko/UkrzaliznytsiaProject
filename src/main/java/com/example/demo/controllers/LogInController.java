package com.example.demo.controllers;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.security.AuthenticationProviderImplementation;
import com.example.demo.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LogInController {

    private final AuthenticationProviderImplementation authenticationProvider;
    private final JWTGenerator tokenGenerator;

    @Autowired
    public LogInController(AuthenticationProviderImplementation authenticationProvider, JWTGenerator tokenGenerator) {
        this.authenticationProvider = authenticationProvider;
        this.tokenGenerator = tokenGenerator;

    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = tokenGenerator.generateToken(auth);
        System.out.println(token);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);

    }

}