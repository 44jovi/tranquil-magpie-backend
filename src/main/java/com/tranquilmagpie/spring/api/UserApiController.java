

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

    @GetMapping("/{id}")
    // @PathVariable binds 'id' value in HTTP request to template var '{id}'
    public ResponseEntity<User> getOneById(@PathVariable int id) {
        User user = this.service.getOneById((long) id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createOne(@RequestBody User user) {
        User createdUser = this.service.createOne(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteOneById(@PathVariable int id) {
        User deletedUser = this.service.deleteOneById((long) id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<User> updateOneById(@PathVariable int id, @RequestBody User user) {
        User patchedUser = this.service.patchOneById((long) id, user);
        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
    }

}
