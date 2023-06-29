package com.tranquilmagpie.spring.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * All users
 */
// Bundle @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
// TODO: check schema name
@Schema(name = "backend", description = "All users")
@JsonTypeName("user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false)
    private UUID uuid;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    // TODO: review field name
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    // TODO: Update tests to use @Builder and review if this is still needed
    public User(String email, String username, String firstName, String lastName, LocalDate dob, String passwordHash) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.passwordHash = passwordHash;
    }

    // Return list of roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // String representation of an authority granted to an Authentication object (principal)
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
