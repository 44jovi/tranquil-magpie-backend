package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersApiController {

    private final UserService service;

    public UsersApiController(UserService service) {
        super();
        this.service = service;
    }

    @GetMapping("users/getAll")
    public List<User> getAll() {
        return this.service.getAll();
    }


}
