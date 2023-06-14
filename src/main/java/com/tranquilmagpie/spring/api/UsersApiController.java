package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/getOneById/{id}")
    // @PathVariable binds 'id' value in HTTP request to template var '{id}'
    public User getOneById(@PathVariable int id) {
        return this.service.getOneById((long) id);
    }

    @PostMapping("/users/createOne")
    public ResponseEntity<User> createOne(@RequestBody User user) {
        User createdUser = this.service.createOne(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }


}
