package com.tranquilmagpie.spring.repo.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShopOrderItemRepo extends JpaRepository<ShopOrderItem, ShopOrderItemId> {

    // TODO: return optional
    List<ShopOrderItem> findByIdShopOrderId(UUID id);

    // TODO: review SQL dialect configuration
    @Query(value = "SELECT SUM(price_total) FROM backend.shop_order_item WHERE shop_order_id = :id", nativeQuery = true)
    BigDecimal getPriceTotalByShopOrderId(@Param("id") UUID id);

}
