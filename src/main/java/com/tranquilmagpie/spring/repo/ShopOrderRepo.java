package com.tranquilmagpie.spring.repo;

import com.tranquilmagpie.spring.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopOrderRepo extends JpaRepository<ShopOrder, UUID> {

    @Override
    Optional<ShopOrder> findById(UUID id);

    Optional<ShopOrder> findAllByUserId(UUID id);

}
