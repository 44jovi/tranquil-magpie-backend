package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class ProductRepoSpringBootTest {

    @Autowired
    private ProductRepo repo;

    private final Product product1 = new Product();

    @BeforeEach
    void setUp() {
        List<Product> products = repo.findAll();

        if (products.size() > 0) {
            if (products.get(0).getName().equals("test product 1")) {
                repo.deleteById(products.get(0).getId());
            }
        }

        product1.setId(UUID.randomUUID());
        product1.setName("test product 1");
        product1.setDescription("test product 1 description");
        product1.setPrice(new BigDecimal("12.34"));
        product1.setStockQty(1);
        product1.setImageFilename("test-product-1-filename");
    }

    @Test
    void testFindById() {
        Product savedProduct = repo.save(product1);

        Optional<Product> foundProductOptional = repo.findById(savedProduct.getId());

        if (foundProductOptional.isPresent()) {
            Product foundProduct = foundProductOptional.get();

            assertEquals(savedProduct.getId(), foundProduct.getId());
            assertEquals("test product 1", foundProduct.getName());
            assertEquals("test product 1 description", foundProduct.getDescription());
            assertEquals(new BigDecimal("12.34"), foundProduct.getPrice());
            assertEquals(1, foundProduct.getStockQty());
            assertEquals("test-product-1-filename", foundProduct.getImageFilename());
        }
    }

    @Test
    void testDeleteById() {
        Product savedProduct = repo.save(product1);
        repo.deleteById(savedProduct.getId());

        Optional<Product> result = repo.findById(savedProduct.getId());

        assertEquals(Optional.empty(), result);
    }

    @Test
    void testAddStockQty() {
        Product product = repo.save(product1);

        repo.addStockQty(product.getId(), 1);

        Optional<Product> patchedProduct = repo.findById(product.getId());

        patchedProduct.ifPresent(
                p -> assertEquals(2, p.getStockQty())
        );
    }

    @Test
    void subtractStockQty() {
        Product product = repo.save(product1);

        repo.subtractStockQty(product.getId(), 1);

        Optional<Product> patchedProduct = repo.findById(product.getId());

        patchedProduct.ifPresent(
                p -> assertEquals(0, p.getStockQty())
        );
    }

}
