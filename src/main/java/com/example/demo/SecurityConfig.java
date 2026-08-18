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
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth->auth
                .requestMatchers("/api/v1/login").permitAll() // Allow anyone to access the login endpoint
                .requestMatchers("/api/v1/logout").permitAll() // Only authenticated users can log out
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
            // THE LOGOUT CONFIGURATION
            .logout(logout -> logout
                .logoutUrl("/api/v1/logout") // The URL React will hit
            
                // 1. Erase the session and clear the context
                .invalidateHttpSession(true) 
                .clearAuthentication(true)   
            
                // 2. Delete the cookie from the user's browser
                .deleteCookies("JSESSIONID") 
            
                //.logoutSuccessUrl("/login")
                // 3. Return a 200 OK instead of an HTML redirect
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
            )
            ;

        return http.build();
    }

   @Value("${spring.security.myuser.adminpwd}")
   private String adminpwd;

   @Value("${spring.security.myuser.userpwd}")
   private String userpwd;

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Create a basic user with only the "USER" role
        UserDetails normalUser = User.builder()
            .username("john")
            .password(passwordEncoder.encode(userpwd)) // "{noop}password" tells Spring NOT to hash this test password
            .roles("USER") 
            .build();

        // Create an admin user with the "ADMIN" role
        UserDetails adminUser = User.builder()
            .username("admin")
            .password(passwordEncoder().encode(adminpwd))
            .roles("ADMIN", "USER")
            .build();

    return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

