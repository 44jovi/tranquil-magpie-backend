package com.tranquilmagpie.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// Spring Boot 3.0+: @Configuration and @EnableWebSecurity need to be together
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // 'final' to ensure auto-injection by Spring
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // On app startup, Spring will first look for a SecurityFilterChain bean
    // SecurityFilterChain processes/applies security filters to incoming requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // TODO: review CSRF protection
        http.csrf().disable()
                .authorizeRequests()
                // Whitelist
                .requestMatchers("/auth/**", "swagger-ui/**")
                .permitAll()
                // Otherwise auth required
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // State authentication provider to be used by Spring
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(
                        // Delete user authentication data
                        (request, response, authentication) -> SecurityContextHolder.clearContext()
                );

        return http.build();
    }

}
