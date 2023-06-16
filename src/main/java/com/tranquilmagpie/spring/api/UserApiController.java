

package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.User;
import com.tranquilmagpie.spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<User> getAll() {
        return this.service.getAll();
    }

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

    @DeleteMapping("/deleteOneById/{id}")
    public User deleteOneById(@PathVariable int id) {
        return this.service.deleteOneById((long) id);
    }


    //    TODO: @PutMapping and use request body?
    @PatchMapping("/updateOneById/{id}")
    public User updateOneById(@PathVariable int id,
                              @RequestParam(name = "email", required = false) String email,
                              @RequestParam(name = "username", required = false) String username,
                              @RequestParam(name = "firstName", required = false) String firstName,
                              @RequestParam(name = "lastName", required = false) String lastName,
                              @RequestParam(name = "dob", required = false) LocalDate dob) {
        return this.service.updateOneById((long) id, email, username, firstName, lastName, dob);
    }

}
