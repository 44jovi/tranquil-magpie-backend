package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class ProductRepoSpringBootTest {

    @Autowired
    private ProductRepo repo;

    private final Product product1 = new Product();

    @BeforeEach
    void setUp() {
        product1.setId(UUID.randomUUID());
        product1.setName("product 1");
        product1.setDescription("product 1 description");
        product1.setPrice(new BigDecimal("12.34"));
        product1.setStockQty(1);
        product1.setImageFilename("product-1-filename");
    }

    @Test
    void testFindById() {
        Product savedProduct = repo.save(product1);

        Optional<Product> foundProductOptional = repo.findById(savedProduct.getId());

        if (foundProductOptional.isPresent()) {
            Product foundProduct = foundProductOptional.get();

            assertEquals(savedProduct.getId(), foundProduct.getId());
            assertEquals("product 1", foundProduct.getName());
            assertEquals("product 1 description", foundProduct.getDescription());
            assertEquals(new BigDecimal("12.34"), foundProduct.getPrice());
            assertEquals(1, foundProduct.getStockQty());
            assertEquals("product-1-filename", foundProduct.getImageFilename());
        }
        repo.deleteById(savedProduct.getId());
    }

    @Test
    void testDeleteById() {
        Product savedProduct = repo.save(product1);
        repo.deleteById(savedProduct.getId());

        Optional<Product> result = repo.findById(savedProduct.getId());

        assertEquals(Optional.empty(), result);
    }

    // TODO: addStockQty
    // TODO: subtractStockQty

}
