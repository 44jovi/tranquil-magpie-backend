package com.tranquilmagpie.spring.repo.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopOrderRepo extends JpaRepository<ShopOrder, UUID> {

    @Override
    Optional<ShopOrder> findById(UUID id);

    Optional<List<ShopOrder>> findAllByUserId(UUID id);

}
