package com.tranquilmagpie.spring.service.impl.product;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import com.tranquilmagpie.spring.service.product.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
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
        return this.repo.findById(id).get();
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
        Product selectedProduct = this.repo.findById(id).get();
        this.repo.deleteById(id);
        return selectedProduct;
    }

    // TODO: for ADMIN role only
    @Override
    public Product patchById(UUID id, Product product) {

        String name = product.getName();
        String description = product.getDescription();
        BigDecimal price = product.getPrice();
        Integer stockQty = product.getStockQty();

        Product selectedProduct = this.getById(id);

        if (name != null)
            selectedProduct.setName(name);
        if (description != null)
            selectedProduct.setDescription(description);
        if (price != null)
            selectedProduct.setPrice(price);
        if (stockQty != null)
            selectedProduct.setStockQty(stockQty);

        return this.repo.save(selectedProduct);
    }

}
