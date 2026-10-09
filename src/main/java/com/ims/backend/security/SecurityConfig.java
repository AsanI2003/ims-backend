package com.ims.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults()) // Works with the @CrossOrigin in controllers
                .authorizeHttpRequests(auth -> auth
                        // Admin can do all 4 operations, User can do CRUD except delete.
                        .requestMatchers(HttpMethod.DELETE, "/api/items/**").hasRole("ADMIN")

                        // Both roles can access the other item endpoints
                        .requestMatchers("/api/items/**").hasAnyRole("ADMIN", "USER")

                        // Allow access to the user check endpoint
                        .requestMatchers("/api/users/me").authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
