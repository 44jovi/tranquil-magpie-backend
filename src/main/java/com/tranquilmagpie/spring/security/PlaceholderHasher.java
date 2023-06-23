package com.tranquilmagpie.spring.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PlaceholderHasher {
    // Strength 10 for balance between security and performance
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public String getHashedPassword(String password) {
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        PlaceholderHasher tempClass = new PlaceholderHasher();

        String hashedPassword = tempClass.getHashedPassword("password");

        System.out.println(hashedPassword);
    }
}
