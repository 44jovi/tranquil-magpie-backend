package com.tranquilmagpie.user.api;

import com.tranquilmagpie.user.model.User;
import com.tranquilmagpie.user.service.UserService;
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
