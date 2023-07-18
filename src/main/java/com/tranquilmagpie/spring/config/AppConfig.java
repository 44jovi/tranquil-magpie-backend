package com.tranquilmagpie.spring.config;

import com.tranquilmagpie.spring.repo.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Declare beans for Spring to implement/inject
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepo repo;

    // Spring Security interface to load user-specific data (used as a user DAO)
    // Multiple are possible for fetching user details from different databases/sources
    @Bean
    public UserDetailsService userDetailsService() {
        // TODO: review replacing this with a lambda
        // TODO: separate class/interface?
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found by that username."));
            }
        };
    }

    // DAO to fetch/process user details
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Retrieve user details from UserDetailsService and authenticate credentials
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        // @sourcesmith: "These can be constructor injected and so immutable."
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    // Authenticate a user
    // Takes an Authentication object (e.g. user credentials)
    // Pass it to AuthenticationProvider(s) for validation
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
