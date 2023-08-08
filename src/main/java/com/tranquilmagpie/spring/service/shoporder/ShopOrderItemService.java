package com.tranquilmagpie.spring.service.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;

import java.util.List;
import java.util.UUID;

public interface ShopOrderItemService {

    // TODO: is this method needed
    List<ShopOrderItem> getAll();

    // TODO: add other methods
    List<ShopOrderItem> getAllByShopOrderId(UUID id);

    ShopOrderItem create(ShopOrderItem shopOrderItem);

    ShopOrderItem updateOrderItems(ShopOrderItem shopOrderItem);

}
