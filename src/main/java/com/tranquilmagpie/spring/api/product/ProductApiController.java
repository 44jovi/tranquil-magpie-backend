package com.tranquilmagpie.spring.api.product;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService service;

    public ProductApiController(ProductService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productes = this.service.getAll();
        return new ResponseEntity<>(productes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        Product product = this.service.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = this.service.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable UUID id) {
        Product deletedProduct = this.service.deleteById(id);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Product> patchById(@PathVariable UUID id, @RequestBody Product product) {
        Product patchedProduct = this.service.patchById(id, product);
        return new ResponseEntity<>(patchedProduct, HttpStatus.OK);
    }
}
