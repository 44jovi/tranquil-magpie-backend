package com.tranquilmagpie.spring.config;

import com.tranquilmagpie.spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// Declare beans for Spring to implement/inject
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepo repo;

    // Spring Security interface to load user-specific data (used as a user DAO)
    @Bean
    public UserDetailsService userDetailsService() {
        // TODO: review replacing this with a lambda
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found by that username."));
            }
        };
    }

}
