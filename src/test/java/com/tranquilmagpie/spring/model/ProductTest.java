package com.tranquilmagpie.spring.model;

import com.tranquilmagpie.spring.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductTest {
    Product product1 = new Product();
    Product product2 = new Product();

    @BeforeEach
    void setUp() {
        product1.setId(UUID.randomUUID());
        product1.setName("test product 1");
        product1.setDescription("test product 1 description");
        product1.setPrice(new BigDecimal("12.34"));
        product1.setStockQty(1);
        product1.setImageFilename("test-product-1-filename");

        product2.setId(UUID.randomUUID());
        product2.setName("test product 2");
        product2.setDescription("test product 2 description");
        product2.setPrice(new BigDecimal("56.78"));
        product2.setStockQty(2);
        product2.setImageFilename("test-product-2-filename");
    }

    @Test
    void testEquals() {
        assertNotEquals(product1, product2);
    }

    @Test
    void testHashCode() {
        assertNotEquals(product1.hashCode(), product2.hashCode());
    }
}
