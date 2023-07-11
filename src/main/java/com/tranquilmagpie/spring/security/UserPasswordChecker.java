package com.tranquilmagpie.spring.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserPasswordChecker {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public boolean isMatch(String passwordString, String passwordHash) {
        return encoder.matches(passwordString, passwordHash);
    }

    public static void main(String[] args) {
        UserPasswordChecker checker = new UserPasswordChecker();

        // Example data
        String passwordString = "password123";
        String passwordHash = "$2a$10$Oq22I28rRna6EC9AcwUSk.2I34s1lf5I8jAN095Gg/YpJqEXznz1C";

        boolean result = checker.isMatch(passwordString, passwordHash);

        System.out.println(result);
    }
}
