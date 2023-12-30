package com.tranquilmagpie.spring.service.impl.product;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import com.tranquilmagpie.spring.service.product.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;

    public ProductServiceImpl(ProductRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public List<Product> getAll() {

        List<Product> products = this.repo.findAll();

        if (products.size() > 0) {
            return products;
        } else {
            throw new RuntimeException("No products found.");
        }
    }

    @Override
    public Product getById(UUID id) {
        Optional<Product> product = this.repo.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("No product found by given ID.");
        }
    }

    // TODO: for ADMIN role only
    @Override
    public Product create(Product product) {
        if (product.getId() == null) {
            Optional<Product> existingProduct = this.repo.findByName(product.getName());
            if (existingProduct.isPresent()) {
                throw new RuntimeException("Product name already exists on an existing product.");
            } else {
                return this.repo.save(product);
            }
        } else {
            throw new RuntimeException("Specifying an ID is not permitted on product creation.");
        }

    }

    // TODO: for ADMIN role only
    @Override
    @Transactional
    public Product deleteById(UUID id) {

        Optional<Product> product = this.repo.findById(id);

        if (product.isPresent()) {
            this.repo.deleteById(id);
            return product.get();
        } else {
            throw new RuntimeException("No product found by given ID.");
        }
    }

    // TODO: for ADMIN role only
    @Override
    public Product patchById(UUID id, Product proposedProduct) {
        Optional<Product> existingProduct = this.repo.findById(id);

        if (existingProduct.isPresent()) {
            Product patchedProduct = existingProduct.get();

            String name = proposedProduct.getName();
            String description = proposedProduct.getDescription();
            BigDecimal price = proposedProduct.getPrice();
            Integer stockQty = proposedProduct.getStockQty();

            if (name != null)
                patchedProduct.setName(name);
            if (description != null)
                patchedProduct.setDescription(description);
            if (price != null)
                patchedProduct.setPrice(price);
            if (stockQty != null)
                patchedProduct.setStockQty(stockQty);

            return this.repo.save(patchedProduct);
        } else {
            throw new RuntimeException("No product found by given ID.");
        }
    }

}
