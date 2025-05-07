//package com.mentorassignment.RetailApplication.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated() // Require authentication for all requests
//                )
//                .httpBasic(Customizer.withDefaults()) // Enable basic authentication
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not for production)
//                .build();
//    }
//}
