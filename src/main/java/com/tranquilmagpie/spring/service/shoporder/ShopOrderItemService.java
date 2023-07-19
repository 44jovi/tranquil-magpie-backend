package com.tranquilmagpie.spring.service.shoporder;

import com.tranquilmagpie.spring.model.shoporder.ShopOrderItem;

import java.util.List;
import java.util.UUID;

public interface ShopOrderItemService {

    // TODO: add more methods once this works correctly
    List<ShopOrderItem> getAllByShopOrderId(UUID id);

}
