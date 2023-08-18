package com.tranquilmagpie.spring.api.product;

import com.tranquilmagpie.spring.api.exception.ApiRequestNotFoundException;
import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductApiController {

    private final ProductService service;

    public ProductApiController(ProductService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = this.service.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        try {
            Product product = this.service.getById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiRequestNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = this.service.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable UUID id) {
        try {
            Product deletedProduct = this.service.deleteById(id);
            return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiRequestNotFoundException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchById(@PathVariable UUID id, @RequestBody Product product) {
        Product patchedProduct = this.service.patchById(id, product);
        return new ResponseEntity<>(patchedProduct, HttpStatus.OK);
    }
}
