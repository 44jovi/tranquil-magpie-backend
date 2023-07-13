package com.tranquilmagpie.spring.api;

import com.tranquilmagpie.spring.model.UserAddress;
import com.tranquilmagpie.spring.service.UserAddressService;
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
    public ResponseEntity<UserAddress> getOneById(@PathVariable UUID id) {
        UserAddress userAddress = this.service.getOneById(id);
        return new ResponseEntity<>(userAddress, HttpStatus.OK);
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<UserAddress> getOneByUserId(@PathVariable UUID id) {
        UserAddress userAddress = this.service.getOneByUserId(id);
        return new ResponseEntity<>(userAddress, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserAddress> createOne(@RequestBody UserAddress userAddress) {
        UserAddress createdUserAddress = this.service.createOne(userAddress);
        return new ResponseEntity<>(createdUserAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserAddress> deleteOneById(@PathVariable UUID id) {
        UserAddress deletedUserAddress = this.service.deleteOneById(id);
        return new ResponseEntity<>(deletedUserAddress, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<UserAddress> patchOneById(@PathVariable UUID id, @RequestBody UserAddress userAddress) {
        UserAddress patchedUserAddress = this.service.patchOneById(id, userAddress);
        return new ResponseEntity<>(patchedUserAddress, HttpStatus.OK);
    }
}
