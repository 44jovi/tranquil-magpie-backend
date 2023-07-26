package com.tranquilmagpie.spring.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    // TODO: review how to confirm user owns/controls email submitted
    private String email;

    private String username;

    private String givenName;

    private String familyName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    private String password;

}
