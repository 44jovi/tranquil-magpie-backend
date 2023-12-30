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

import static com.tranquilmagpie.spring.model.user.Role.ADMIN;

// Spring Boot 3.0+: @Configuration and @EnableWebSecurity need to be together
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // 'final' to ensure auto-injection by Spring(?)
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // On app startup, Spring will first look for a SecurityFilterChain bean
    // SecurityFilterChain processes/applies security filters to incoming requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        // TODO: review CSRF protection
        security.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()

                // Public
                .requestMatchers(
                        // TODO: review why this doesn't work for "/auth/register"
                        "/swagger",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/auth/**",
                        "/auth/register",
                        "/product/"
                ).permitAll()

                // Admin
                // TODO: add admin-only endpoints
                .requestMatchers("/admin")
                // TODO: review usage of roles instead of authority
                .hasAuthority(ADMIN.name())

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

        return security.build();
    }

}
