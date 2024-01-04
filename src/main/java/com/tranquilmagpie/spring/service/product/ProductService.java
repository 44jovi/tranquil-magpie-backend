package com.tranquilmagpie.spring.service.product;

import com.tranquilmagpie.spring.model.product.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAll();

    Product getById(UUID id);

    Product create(Product product);

    Product deleteById(UUID id);

    Product patchById(UUID id, Product product);
}
