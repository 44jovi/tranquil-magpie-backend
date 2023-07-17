package com.tranquilmagpie.spring.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            // Intercept each response/request to allow extraction/manipulation of the data
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Call next filter in chain, or resource if end of chain
            filterChain.doFilter(request, response);
            return;
        }

        // TODO: make "Bearer " a constant and use its length
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);

        // Check user exists in token and not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.isJwtValid(jwt, userDetails)) {
                // Token required by SecurityContextHolder to update the security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        // TODO: review / update
                        null,
                        userDetails.getAuthorities()
                );
                // Add further information to the token from client request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Mark user as authenticated in the security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Pass request/response to the next filter in chain
        filterChain.doFilter(request, response);
    }
}
