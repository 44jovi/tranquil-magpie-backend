package com.tranquilmagpie.spring.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * All users
 */
// Bundles @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
// TODO: check schema name
@Schema(name = "backend", description = "All users")
@JsonTypeName("user")
public class User {
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

    // TODO: Update tests to use @Builder. Current purpose of this constructor is for tests
    public User(String email, String username, String firstName, String lastName, LocalDate dob, String passwordHash) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.passwordHash = passwordHash;
    }

}
