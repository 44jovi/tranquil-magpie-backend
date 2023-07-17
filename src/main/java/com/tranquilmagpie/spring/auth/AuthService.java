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

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .givenName(request.getFirstName())
                .familyName(request.getLastName())
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
        // TODO: refer to @sourcesmith comment re propagating exceptions to last resort handler
        User user = repo.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwt).build();
    }

    // TODO: review if making this work with JWT's is required, especially if implementing session-based auth in future
    public AuthResponse logout(LogoutRequest request) {
        // TODO: redirect client if user is not logged in?
        User user = repo.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.revokeToken(user);

        return AuthResponse.builder().token(jwt).build();
    }

}
