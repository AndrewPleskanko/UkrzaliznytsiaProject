package com.example.demo.security.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImplementation implements AuthenticationProvider {
    private static final Logger logger = LogManager.getLogger();

    private final UserDetailsServiceImplementation userDetailsService;
  //  private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationProviderImplementation(UserDetailsServiceImplementation userDetailsService) {
        this.userDetailsService = userDetailsService;
      //  this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userDetailsService == null) {
            throw new InternalAuthenticationServiceException("User service is null");
        }

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("No such user was found");
        }

        if (user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        } else {
            throw new AuthenticationServiceException("Unable authenticate user due to some problems");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}