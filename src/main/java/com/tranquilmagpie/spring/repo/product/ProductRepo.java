package com.tranquilmagpie.spring.repo.product;

import com.tranquilmagpie.spring.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {

    @Override
    Optional<Product> findById(UUID id);

}
