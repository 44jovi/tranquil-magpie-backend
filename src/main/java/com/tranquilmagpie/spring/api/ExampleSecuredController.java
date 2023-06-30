package com.tranquilmagpie.spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class ExampleSecuredController {

    @GetMapping
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("you have logged in - welcome");
    }

}
