package org.yl.oop_web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.yl.oop_web.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean // Hashes the password for security
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // This method accepts an HTTP security object
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/signup", "/login" , "/scholarships ","/profile/{username}").permitAll() // Allow access to these endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/redirect", true)
                        .failureUrl("/login?error=true") // Handles login errors
                        .permitAll() // Allow everyone to see the login page
                )
                .logout(LogoutConfigurer::permitAll // Allow everyone to log out
                );

        return http.build();
    }
}