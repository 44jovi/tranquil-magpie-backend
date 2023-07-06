package com.tranquilmagpie.spring.auth;

import com.tranquilmagpie.spring.config.JwtService;
import com.tranquilmagpie.spring.model.Role;
import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        // TODO: review using 'var'?
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                // BCryptPasswordEncoder (see AppConfig)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repo.save(user);
        String jwt = jwtService.generateToken(user);

        /* Lombok generates a builder class for AuthResponse (AuthResponseBuilder)
        that creates (builds) a new instance of AuthResponse */
        return AuthResponse.builder().token(jwt).build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        // TODO: handle exception if empty optional returned
        User user = repo.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwt).build();
    }

}