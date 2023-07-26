package com.tranquilmagpie.spring.api.user;

import com.tranquilmagpie.spring.model.user.User;
import com.tranquilmagpie.spring.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserApiController {

    private final UserService service;

    public UserApiController(UserService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = this.service.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id) {
        User user = this.service.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        User user = this.service.getByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = this.service.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteById(@PathVariable UUID id) {
        User deletedUser = this.service.deleteById(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<User> patchById(@PathVariable UUID id, @RequestBody User user) {
        User patchedUser = this.service.patchById(id, user);
        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
    }
}
