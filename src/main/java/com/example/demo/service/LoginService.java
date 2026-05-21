package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public SecurityContext checkCredentials(String username, String password) {
        // Implementation for login logic
        // This is where you would typically check the username and password against a database
        // and generate a token or session if the credentials are valid.

        try {
            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
            username, password);

            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);  
            SecurityContextHolder.setContext(context);  
            return context;

        } catch (BadCredentialsException e) {
            // 3. The password or username was wrong.
            return null;
        }
    }



}
