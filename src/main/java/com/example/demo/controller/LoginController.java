package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.LoginRequest;
import com.example.demo.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1")
public class LoginController {
    
    private LoginService loginService;

    public LoginController(LoginService loginService) {
       this.loginService = loginService;
    }

    private SecurityContextRepository securityContextRepository =
        new HttpSessionSecurityContextRepository();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        SecurityContext context = loginService.checkCredentials(loginRequest.username(), loginRequest.password());
        if (context == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // This tells Spring to save the context into the HTTP Session.
        // Tomcat will automatically generate a "Set-Cookie: JSESSIONID=..." header.
        securityContextRepository.saveContext(context, request, response);

        return ResponseEntity.ok("Login successful");
        
    }
    
}

record LoginRequest(String username, String password) {}