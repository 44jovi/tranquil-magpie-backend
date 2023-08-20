package com.tranquilmagpie.spring.service.impl.product;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    ProductServiceImpl productServiceImpl;
    ProductRepo ProductRepoMock;

    @BeforeEach
    void setUp() {
        ProductRepoMock = mock(ProductRepo.class);
        productServiceImpl = new ProductServiceImpl(ProductRepoMock);
    }

    @Test
    void getAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        List<Product> productsEmpty = new ArrayList<>();

        when(ProductRepoMock.findAll()).thenReturn(products);
        assertEquals(2, productServiceImpl.getAll().size());

        when(ProductRepoMock.findAll()).thenReturn(productsEmpty);
        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> productServiceImpl.getAll()
        );
        assertEquals("No products found.", e.getMessage());
    }

    @Test
    void getById() {
        when(ProductRepoMock.findById(any(UUID.class)))
                .thenReturn(Optional.of(new Product()));
        assertEquals(
                Product.class,
                productServiceImpl.getById(UUID.randomUUID()).getClass()
        );

        when(ProductRepoMock.findById(any(UUID.class)))
                .thenReturn(Optional.empty());
        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> productServiceImpl.getById(UUID.randomUUID())
        );
        assertEquals(
                "No product found by given ID.", e.getMessage()
        );
    }

//
//    @Test
//    void create() {
//    }
//
//    @Test
//    void deleteById() {
//    }
//
//    @Test
//    void patchById() {
//    }
}
