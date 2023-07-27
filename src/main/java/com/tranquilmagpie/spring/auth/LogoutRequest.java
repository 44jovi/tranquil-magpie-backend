package com.tranquilmagpie.spring.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: review/add constraints on generated constructors and setters
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {

    // TODO: review if more required in a logout request
    private String username;

}
