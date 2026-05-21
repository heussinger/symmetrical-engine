package com.example.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth->auth
                .requestMatchers("/api/v1/login").permitAll() // Allow anyone to access the login endpoint
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN")
                .anyRequest().permitAll()
            )
            //.formLogin(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            // This tells Spring: "If an unauthenticated user tries to access a secured 
            // endpoint, do NOT redirect them to an HTML login page. Just send a 401 error."
            .exceptionHandling(exc -> exc
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            )
            ;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Create a basic user with only the "USER" role
        UserDetails normalUser = User.builder()
            .username("john")
            .password("{noop}pwd") // {noop} tells Spring NOT to hash this test password
            .roles("USER") 
            .build();

        // Create an admin user with the "ADMIN" role
        UserDetails adminUser = User.builder()
            .username("admin")
            .password("{noop}supersecret")
            .roles("ADMIN", "USER")
            .build();

    return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

