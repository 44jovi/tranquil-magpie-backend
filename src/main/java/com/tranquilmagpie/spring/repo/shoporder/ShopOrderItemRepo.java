package com.tranquilmagpie.spring.repo.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopOrderItemRepo extends JpaRepository<ShopOrderItem, ShopOrderItemKey> {

    Optional<List<ShopOrderItem>> findAllByShopOrderId(UUID id);

}
