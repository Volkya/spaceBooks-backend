package com.reader.spacebooks.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http : HttpSecurity) : SecurityFilterChain{
        http
            .authorizeHttpRequests  { authz ->
                authz
                    .requestMatchers("/api/health").permitAll()
                    .requestMatchers("/encryption/rsa/encrypt").permitAll()
                    .requestMatchers("/encryption/rsa/decrypt").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { csrf -> csrf.disable() }
        return http.build()
    }
}