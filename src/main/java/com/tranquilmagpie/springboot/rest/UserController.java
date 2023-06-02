package com.tranquilmagpie.springboot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: constructor with UserService parameter

@RestController
public class UserController {

    @GetMapping("/users/getAll")
    public String tempMsg() {
        return "return all users - WIP";
    }
}
