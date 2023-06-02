package com.tranquilmagpie.springboot.rest;

import com.tranquilmagpie.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    User userExample1 = new User(44, "test@test.com", "username123", "Wendy", "Kit", "1904-03-02");

    @GetMapping("/")
    public String welcome() {
        return "welcome | example user: " + userExample1;
    }
}
