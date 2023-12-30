package com.tranquilmagpie.spring.service.impl.product;

import com.tranquilmagpie.spring.model.product.Product;
import com.tranquilmagpie.spring.repo.product.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    Product product1;
    Product product2;

    UUID product2Id;

    @BeforeEach
    void setUp() {
        ProductRepoMock = mock(ProductRepo.class);
        productServiceImpl = new ProductServiceImpl(ProductRepoMock);

        product1 = new Product(
                "",
                "",
                new BigDecimal("1"),
                1,
                ""
        );

        product2 = new Product(
                UUID.randomUUID(),
                "",
                "",
                new BigDecimal("1"),
                1,
                ""
        );

        product2Id = product2.getId();
    }

    @Test
    void getAll() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(ProductRepoMock.findAll()).thenReturn(products);

        assertEquals(2, productServiceImpl.getAll().size());
    }

    @Test
    void getAllNoneFound() {
        List<Product> productsEmpty = new ArrayList<>();

        when(ProductRepoMock.findAll()).thenReturn(productsEmpty);

        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> productServiceImpl.getAll()
        );
        assertEquals("No products found.", e.getMessage());
    }

    @Test
    void getById() {
        when(ProductRepoMock.findById(product2Id))
                .thenReturn(Optional.of(product1));

        assertEquals(
                product1,
                productServiceImpl.getById(product2Id
                ));
    }

    @Test
    void getByIdNotFound() {
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

    @Test
    void createSuccessful() {
        when(ProductRepoMock.save(product1)).thenReturn(product1);
        assertEquals(product1, productServiceImpl.create(product1));
    }

    @Test
    void createWithId() {
        when(ProductRepoMock.save(product2)).thenReturn(product2);
        RuntimeException e = assertThrows(RuntimeException.class, () -> productServiceImpl.create(product2));

        assertEquals(
                "Specifying an ID is not permitted on product creation.", e.getMessage()
        );
    }

    @Test
    void createNameAlreadyExists() {
        when(ProductRepoMock.findByName(product1.getName())).thenReturn(Optional.of(product1));
        RuntimeException e = assertThrows(RuntimeException.class, () -> productServiceImpl.create(product1));

        assertEquals(
                "Product name already exists on an existing product.", e.getMessage()
        );
    }

    @Test
    void deleteById() {
        when(ProductRepoMock.findById(product2Id))
                .thenReturn(Optional.of(product1));

        assertEquals(product1, productServiceImpl.deleteById(product2Id));
    }

    @Test
    void deleteByIdNotFound() {
        when(ProductRepoMock.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> productServiceImpl.deleteById(UUID.randomUUID())
        );
        assertEquals(
                "No product found by given ID.", e.getMessage()
        );
    }

    @Test
    void patchById() {
        when(ProductRepoMock.findById(product2Id)).thenReturn(Optional.of(product2));
        when(ProductRepoMock.save(product2)).thenReturn(product2);

        assertEquals(product2, productServiceImpl.patchById(product2Id, product2));
    }

    @Test
    void patchByIdNotFound() {
        when(ProductRepoMock.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> productServiceImpl.patchById(UUID.randomUUID(), product2)
        );
        assertEquals(
                "No product found by given ID.", e.getMessage()
        );
    }
}
