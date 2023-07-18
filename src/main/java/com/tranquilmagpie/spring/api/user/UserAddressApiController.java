package com.tranquilmagpie.spring.api.user;

import com.tranquilmagpie.spring.model.user.UserAddress;
import com.tranquilmagpie.spring.service.user.UserAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/postal-address")
public class UserAddressApiController {

    private final UserAddressService service;

    public UserAddressApiController(UserAddressService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserAddress>> getAll() {
        List<UserAddress> userAddresses = this.service.getAll();
        return new ResponseEntity<>(userAddresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> getById(@PathVariable UUID id) {
        UserAddress userAddress = this.service.getById(id);
        return new ResponseEntity<>(userAddress, HttpStatus.OK);
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<UserAddress> getByUserId(@PathVariable UUID id) {
        UserAddress userAddress = this.service.getByUserId(id);
        return new ResponseEntity<>(userAddress, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserAddress> create(@RequestBody UserAddress userAddress) {
        UserAddress createdUserAddress = this.service.create(userAddress);
        return new ResponseEntity<>(createdUserAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserAddress> deleteById(@PathVariable UUID id) {
        UserAddress deletedUserAddress = this.service.deleteById(id);
        return new ResponseEntity<>(deletedUserAddress, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<UserAddress> patchById(@PathVariable UUID id, @RequestBody UserAddress userAddress) {
        UserAddress patchedUserAddress = this.service.patchById(id, userAddress);
        return new ResponseEntity<>(patchedUserAddress, HttpStatus.OK);
    }
}
