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
        return this.repo.findAll();
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
        return this.repo.save(product);
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
            Product updatedProduct = existingProduct.get();

            String name = proposedProduct.getName();
            String description = proposedProduct.getDescription();
            BigDecimal price = proposedProduct.getPrice();
            Integer stockQty = proposedProduct.getStockQty();

            if (name != null)
                updatedProduct.setName(name);
            if (description != null)
                updatedProduct.setDescription(description);
            if (price != null)
                updatedProduct.setPrice(price);
            if (stockQty != null)
                updatedProduct.setStockQty(stockQty);

            return this.repo.save(updatedProduct);
        } else {
            throw new RuntimeException("No product found by given ID.");
        }
    }

}
