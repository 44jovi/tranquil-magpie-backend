

package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // TODO: ResponseEntity
    @GetMapping("/{id}")
    // @PathVariable binds 'id' value in HTTP request to template var '{id}'
    public User getOneById(@PathVariable int id) {
        return this.service.getOneById((long) id);
    }

    @PostMapping("/")
    public ResponseEntity<User> createOne(@RequestBody User user) {
        User createdUser = this.service.createOne(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // TODO: ResponseEntity
    @DeleteMapping("/delete/{id}")
    public User deleteOneById(@PathVariable int id) {
        return this.service.deleteOneById((long) id);
    }

    // TODO: ResponseEntity
    @PatchMapping("/patch/{id}")
    public User updateOneById(@PathVariable int id, @RequestBody User user) {
        return this.service.patchOneById((long) id, user);
    }

}
