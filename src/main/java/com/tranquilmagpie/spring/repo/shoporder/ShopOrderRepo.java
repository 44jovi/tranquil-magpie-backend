package com.tranquilmagpie.spring.repo.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShopOrderRepo extends JpaRepository<ShopOrder, UUID> {

    @Override
    Optional<ShopOrder> findById(UUID id);

    Optional<List<ShopOrder>> findAllByUserId(UUID id);

    // TODO: use service class instead to avoid using @Transactional in repo
    @Transactional
    @Modifying
    @Query(value = "UPDATE backend.shop_order SET order_total = :orderTotal WHERE id = :id", nativeQuery = true)
    void updateOrderTotal(@Param("id") UUID id, @Param("orderTotal") BigDecimal orderTotal);

}
