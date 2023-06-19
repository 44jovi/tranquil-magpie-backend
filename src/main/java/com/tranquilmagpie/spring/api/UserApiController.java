

package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<User> getOneById(@PathVariable UUID id) {
        User user = this.service.getOneById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> createOne(@RequestBody User user) {
        User createdUser = this.service.createOne(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Optional<User>> deleteOneById(@PathVariable UUID id) {
//        Optional<User> deletedUser = this.service.deleteOneById(id);
//        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
//    }
//TODO: reimplement this method
//    @PatchMapping("/patch/{id}")
//    public ResponseEntity<User> updateOneById(@PathVariable UUID id, @RequestBody User user) {
//        User patchedUser = this.service.patchOneById(id, user);
//        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
//    }

}
