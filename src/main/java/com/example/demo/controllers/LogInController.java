package com.example.demo.controllers;

import com.example.demo.security.AuthenticationProviderImplementation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogInController {

    private final AuthenticationProviderImplementation authenticationProvider;

    @Autowired
    public LogInController(AuthenticationProviderImplementation authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, Authentication authentication) {
        try {
            Authentication auth = authenticationProvider.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/main-page";
        } catch (AuthenticationException e) {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/login")
    public String signInPage(Model model, String error, String logout) {
        if (logout != null) {
            model.addAttribute("logout", true);
        }
        if (error != null) {
            model.addAttribute("error", true);
        }
        return "login";
    }
}