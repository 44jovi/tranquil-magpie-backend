package com.tranquilmagpie.spring.repo.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;
import com.tranquilmagpie.spring.model.shoporder.ShopOrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderItemRepo extends JpaRepository<ShopOrderItem, ShopOrderItemKey> {

}
