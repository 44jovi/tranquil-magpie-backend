package com.tranquilmagpie.spring.repo.product;

import com.tranquilmagpie.spring.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {

    @Override
    Optional<Product> findById(UUID id);

    Optional<Product> findByName(String name);

    // TODO: use service class instead to avoid using @Transactional in repo
    // TODO: review SQL dialect configuration
    @Transactional
    @Modifying
    @Query(value = "UPDATE {h-schema}product SET stock_qty = stock_qty + :amount WHERE id = :id", nativeQuery = true)
    int addStockQty(@Param("id") UUID id, @Param("amount") int amount);

    // TODO: use service class instead to avoid using @Transactional in repo
    // TODO: review SQL dialect configuration
    @Transactional
    @Modifying
    @Query(value = "UPDATE {h-schema}product SET stock_qty = stock_qty - :amount WHERE id = :id", nativeQuery = true)
    int subtractStockQty(@Param("id") UUID id, @Param("amount") int amount);

}
