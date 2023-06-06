package com.tranquilmagpie.springboot.rest;

import com.tranquilmagpie.springboot.domain.User;
import com.tranquilmagpie.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/users/get/{id}")
  // @PathVariable binds 'id' value in HTTP request to template var '{id}'
  public User get(@PathVariable int id) {
    return this.service.get((long) id);
  }

  @PostMapping("/users/create")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = this.service.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
