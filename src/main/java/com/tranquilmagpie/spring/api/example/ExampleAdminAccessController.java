package com.tranquilmagpie.spring.api.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ExampleAdminAccessController {

    @GetMapping
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("hello, admin.");
    }

}
