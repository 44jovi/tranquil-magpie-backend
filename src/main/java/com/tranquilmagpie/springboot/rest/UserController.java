package com.tranquilmagpie.springboot.rest;

import com.tranquilmagpie.springboot.domain.User;
import com.tranquilmagpie.springboot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        super();
        this.service = service;
    }

    @GetMapping("/users/getAll")
    public List<User> getAll() {
        return this.service.getAll();
    }
}
